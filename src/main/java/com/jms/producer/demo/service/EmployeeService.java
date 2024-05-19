package com.jms.producer.demo.service;

import com.jms.producer.demo.dao.EmployeeRepository;
import com.jms.producer.demo.dao.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Service
public class EmployeeService {

    private static final Logger logger = Logger.getLogger(EmployeeService.class.getName());


    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JmsProducer jmsProducer;

    public Employee retrieveEmployee(Long id){
        return employeeRepository.findById(id).get();
    }

    public List<Employee> retrieveEmployees(){
        return employeeRepository.findAll();
    }

    public void sendEmployeeDetails(){
        List<Employee> employees = retrieveEmployees();
        Flux.fromIterable(employees).doOnNext(employee -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    jmsProducer.sendMessage(employee.toString());

                })
                .doOnComplete(() -> logger.info("All employees processed"))
                .doOnError(error -> logger.severe("Error occurred: " + error.getMessage())).subscribe();
        /*employees.forEach(emp -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            jmsProducer.sendMessage(emp.toString());
        });*/
    }
}
