package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Employee;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.Payment;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EmployeeService;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping(value = "/employees/{employee}/payments")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR', 'ROLE_USER')") // todo only one user
public class EmployeePaymentsController {

    private EmployeeService employeeService;

    private PaymentService paymentService;

    @Autowired
    public EmployeePaymentsController(EmployeeService employeeService, PaymentService paymentService) {
        this.employeeService = employeeService;
        this.paymentService = paymentService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Collection<Payment>> getEmployeePayments(@PathVariable("employee") UUID id) {
        return new ResponseEntity<>(employeeService.findById(id).getPayments(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            params = {"start-date", "finish-date"})
    public ResponseEntity<Collection<Payment>> getEmployeePaymentsByPeriod(
            @PathVariable("employee") UUID id,
            @RequestParam("start-date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("finish-date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date finishDate) {
        Employee employee = employeeService.findById(id);
        Collection<Payment> employeePayments = paymentService.getByEmployeeAndPeriod(employee, startDate, finishDate);
        return new ResponseEntity<>(employeePayments, HttpStatus.OK);
    }

}
