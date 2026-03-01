package com.example.HmsApplication.queue.service;

import org.springframework.stereotype.Service;

import java.util.List;

import com.example.HmsApplication.queue.entity.Queue;
import com.example.HmsApplication.queue.repository.QueueRepository;
import com.example.HmsApplication.reception.repository.VisitRepository;
import com.example.HmsApplication.reception.repository.PaymentRepository;
import com.example.HmsApplication.reception.entity.Visit;

@Service
public class QueueService {

    private QueueRepository queueRepository;
    private VisitRepository visitRepository;
    private PaymentRepository paymentRepository;

    public QueueService(QueueRepository queueRepository,
                        VisitRepository visitRepository,
                        PaymentRepository paymentRepository) {
        this.queueRepository = queueRepository;
        this.visitRepository = visitRepository;
        this.paymentRepository = paymentRepository;
    }

    public Queue addPatientToQueue(Queue queue) {

        Visit visit = visitRepository.findById(queue.getVisitId())
                .orElseThrow(() -> new RuntimeException("Visit not found"));

        if (visit.getVisitStatus() != Visit.VisitStatus.ACTIVE) {
            throw new RuntimeException("Visit not active");
        }

        if (paymentRepository.findByVisitId(queue.getVisitId()).isEmpty()) {
            throw new RuntimeException("Payment required before queue");
        }

        int nextQueueNumber =
                queueRepository.countByDoctorId(queue.getDoctorId()) + 1;

        queue.setQueueNumber(nextQueueNumber);
        queue.setStatus(Queue.QueueStatus.WAITING);

        return queueRepository.save(queue);
    }

    public List<Queue> getQueueByDoctor(String doctorId) {
        return queueRepository
                .findByDoctorIdAndStatusOrderByQueueNumberAsc(
                        doctorId,
                        Queue.QueueStatus.WAITING
                );
    }

    public Queue updateQueueStatus(String queueId, String status) {

        Queue queue = queueRepository.findById(queueId)
                .orElseThrow(() -> new RuntimeException("Queue not found"));

        queue.setStatus(Queue.QueueStatus.valueOf(status));

        if (status.equals("DONE")) {
            Visit visit = visitRepository.findById(queue.getVisitId()).get();
            visit.setVisitStatus(Visit.VisitStatus.COMPLETED);
            visitRepository.save(visit);
        }

        return queueRepository.save(queue);
    }
}