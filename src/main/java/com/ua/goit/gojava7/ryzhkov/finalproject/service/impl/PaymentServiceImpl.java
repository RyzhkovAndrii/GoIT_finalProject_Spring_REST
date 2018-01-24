package com.ua.goit.gojava7.ryzhkov.finalproject.service.impl;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Employee;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.Payment;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.PaymentRepository;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment findById(UUID id) {
        return paymentRepository.findOne(id);
    }

    @Override
    public Collection<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment save(Payment payment) {
        payment.setDate(new Date());
        return paymentRepository.save(payment);
    }

    @Override
    public void delete(Payment payment) {
        paymentRepository.delete(payment);
    }

    @Override
    public void delete(UUID id) {
        paymentRepository.delete(id);
    }

    @Override
    public Collection<Payment> getByEmployeeAndPeriod(Employee employee, Date startDate, Date finishDate) {
        // todo dates check
        return paymentRepository.getByEmployeeAndDateIsBetween(employee, startDate, finishDate);
    }

}
