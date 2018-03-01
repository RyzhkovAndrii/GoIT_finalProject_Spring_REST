package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.converter.ModelConversionService;
import com.ua.goit.gojava7.ryzhkov.finalproject.dto.PaymentResponse;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.Employee;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.Payment;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EmployeeService;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.PaymentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping(value = "/employees/{employee}/payments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR', 'ROLE_USER')") // todo only one user
@RequiredArgsConstructor
public class EmployeePaymentsController {

    private final EmployeeService employeeService;

    private final PaymentService paymentService;

    private final ModelConversionService conversionService;

    @ApiOperation(value = "view list of user's payments")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<PaymentResponse> getEmployeePayments(@PathVariable("employee") UUID id) {
        return conversionService.convert(employeeService.findById(id).getPayments(), PaymentResponse.class);
    }

    @ApiOperation(value = "view list of user's payments for period")
    @GetMapping(params = {"start-date", "finish-date"})
    @ResponseStatus(HttpStatus.OK)
    public Collection<PaymentResponse> getEmployeePaymentsByPeriod(
            @PathVariable("employee") UUID id,
            @RequestParam("start-date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("finish-date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date finishDate) {
        Employee employee = employeeService.findById(id);
        Collection<Payment> employeePayments = paymentService.getByEmployeeAndPeriod(employee, startDate, finishDate);
        return conversionService.convert(employeePayments, PaymentResponse.class);
    }

}
