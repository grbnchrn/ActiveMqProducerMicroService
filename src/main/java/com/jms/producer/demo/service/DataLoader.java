package com.jms.producer.demo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jms.producer.demo.dao.EmployeeRepository;
import com.jms.producer.demo.dao.model.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;

@Service
public class DataLoader {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostConstruct
    public void loadData() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Employee> employees = mapper.readValue(
                    new ClassPathResource("employees.json").getFile(),
                    new TypeReference<List<Employee>>() {}
            );
            employeeRepository.saveAll(employees);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

