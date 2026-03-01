package com.example.HmsApplication.queue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.HmsApplication.queue.entity.Queue;

import java.util.List;

public interface QueueRepository extends JpaRepository<Queue, String> {

    List<Queue> findByDoctorIdAndStatusOrderByQueueNumberAsc(
            String doctorId,
            Queue.QueueStatus status
    );

    int countByDoctorId(String doctorId);
}
