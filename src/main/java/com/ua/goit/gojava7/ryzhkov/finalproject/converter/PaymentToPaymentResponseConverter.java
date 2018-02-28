package com.ua.goit.gojava7.ryzhkov.finalproject.converter;

import com.ua.goit.gojava7.ryzhkov.finalproject.dto.PaymentResponse;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.Payment;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PaymentToPaymentResponseConverter implements Converter<Payment, PaymentResponse> {

    @Override
    public PaymentResponse convert(Payment payment) {
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setId(payment.getId());
        paymentResponse.setDate(payment.getDate());
        paymentResponse.setWorkingHours(payment.getWorkingHours());
        paymentResponse.setSalary(payment.getSalary());
        paymentResponse.setEmployeeId(payment.getEmployee().getId());
        return paymentResponse;
    }

}
