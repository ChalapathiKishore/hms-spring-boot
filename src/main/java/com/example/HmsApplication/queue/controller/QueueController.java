package com.example.HmsApplication.queue.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.HmsApplication.queue.entity.Queue;
import com.example.HmsApplication.queue.service.QueueService;

@RestController
@RequestMapping("/api/queue")
public class QueueController {

    private QueueService queueService;

    // ✅ Manual constructor injection
    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    @PostMapping
    public Queue addToQueue(@RequestBody Queue queue) {
        return queueService.addPatientToQueue(queue);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<Queue> getQueueByDoctor(@PathVariable String doctorId) {
        return queueService.getQueueByDoctor(doctorId);
    }

    @PutMapping("/{queueId}/status")
    public Queue updateQueueStatus(
            @PathVariable String queueId,
            @RequestParam String status
    ) {
        return queueService.updateQueueStatus(queueId, status);
    }
}
