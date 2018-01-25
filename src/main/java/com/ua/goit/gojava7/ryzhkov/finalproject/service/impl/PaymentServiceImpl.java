package com.ua.goit.gojava7.ryzhkov.finalproject.service.impl;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Employee;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.Payment;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.PaymentRepository;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EmployeeService;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Collection;
import java.util.Date;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;

    private EmployeeService employeeService;

    private JavaMailSender sender;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository,
                              EmployeeService employeeService,
                              JavaMailSender sender) {
        this.paymentRepository = paymentRepository;
        this.employeeService = employeeService;
        this.sender = sender;
    }

    @Override
    public Collection<Payment> getByEmployeeAndPeriod(Employee employee, Date startDate, Date finishDate) {
        // todo dates check
        return paymentRepository.getByEmployeeAndDateIsBetween(employee, startDate, finishDate);
    }

    private Payment createPaymentByEmployee(Employee employee) {
        Payment payment = new Payment();
        payment.setDate(new Date());
        double currentWorkingHours = employee.getCurrentWorkingHours();
        payment.setWorkingHours(currentWorkingHours);
        double salary = currentWorkingHours * employee.getHourlyRate();
        payment.setSalary(salary);
        payment.setEmployee(employee);
        employeeService.nullableCurrentWorkingHours(employee);
        return paymentRepository.save(payment);
    }

    private void sendPaymentToEmployeeByEmail(Payment payment) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        Employee employee = payment.getEmployee();
        String email = employee.getEmail();
        String subject = "Salary. " + payment.getDate() + " "
                + employee.getFirstName() + " " + employee.getLastName();
        String text = "Salary = " + payment.getSalary() + "\n" +
                "Date: " + payment.getDate() + "\n" +
                "Working Hours = " + payment.getWorkingHours() + "\n" +
                "Employee: " + employee.getFirstName() + "" + employee.getLastName();
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(text);
        sender.send(message);
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void autoCalculateAndSendPaymentsForAllEmployeeOnePerMonth() throws MessagingException {
        Collection<Employee> employees = employeeService.findAll();
        for (Employee employee : employees) {
            Payment payment = createPaymentByEmployee(employee);
            sendPaymentToEmployeeByEmail(payment);
        }
    }

}
