package com.jms.producer.demo.controller;

import com.jms.producer.demo.dao.model.Employee;
import com.jms.producer.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getEmployee")
    public Employee retrieveEmployee(@RequestParam Long id) {
        return employeeService.retrieveEmployee(id);
    }

    @GetMapping("/sendData")
    public String sendEmployeeData() {
        employeeService.sendEmployeeDetails();
        return "Started";
    }
}