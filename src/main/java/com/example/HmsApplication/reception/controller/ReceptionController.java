package com.example.HmsApplication.reception.controller;

import org.springframework.web.bind.annotation.*;

import com.example.HmsApplication.reception.entity.Visit;
import com.example.HmsApplication.reception.entity.Payment;
import com.example.HmsApplication.reception.service.VisitService;
import com.example.HmsApplication.reception.service.PaymentService;

@RestController
@RequestMapping("/api/reception")
public class ReceptionController {

    private VisitService visitService;
    private PaymentService paymentService;

    // ✅ Manual constructor injection
    public ReceptionController(VisitService visitService,
                               PaymentService paymentService) {
        this.visitService = visitService;
        this.paymentService = paymentService;
    }

    @PostMapping("/visits")
    public Visit createVisit(@RequestBody Visit visit) {
        return visitService.createVisit(visit);
    }

    @PostMapping("/payments")
    public Payment makePayment(@RequestBody Payment payment) {
        return paymentService.processPayment(payment);
    }
}