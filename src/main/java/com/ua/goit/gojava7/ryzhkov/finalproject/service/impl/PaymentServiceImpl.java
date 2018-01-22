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
public class PaymentServiceImpl extends BaseEntityServiceImpl<Payment, UUID> implements PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        super(paymentRepository);
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment save(Payment payment) {
        payment.setDate(new Date());
        return super.save(payment);
    }

    @Override
    public Collection<Payment> getByEmployeeAndPeriod(Employee employee, Date startDate, Date finishDate) {
        return paymentRepository.getByEmployeeAndDateIsBetween(employee, startDate, finishDate);
    }

}
