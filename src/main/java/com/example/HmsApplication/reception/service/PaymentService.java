package com.example.HmsApplication.reception.service;

import org.springframework.stereotype.Service;

import com.example.HmsApplication.reception.entity.Payment;
import com.example.HmsApplication.reception.entity.Visit;
import com.example.HmsApplication.reception.repository.PaymentRepository;
import com.example.HmsApplication.reception.repository.VisitRepository;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;
    private VisitRepository visitRepository;

    public PaymentService(PaymentRepository paymentRepository,
                          VisitRepository visitRepository) {
        this.paymentRepository = paymentRepository;
        this.visitRepository = visitRepository;
    }

    public Payment processPayment(Payment payment) {

        Visit visit = visitRepository.findById(payment.getVisitId())
                .orElseThrow(() -> new RuntimeException("Visit not found"));

        if (paymentRepository.findByVisitId(payment.getVisitId()).isPresent()) {
            throw new RuntimeException("Payment already done");
        }

        payment.setAmount(100); // basic static fee

        return paymentRepository.save(payment);
    }
}
