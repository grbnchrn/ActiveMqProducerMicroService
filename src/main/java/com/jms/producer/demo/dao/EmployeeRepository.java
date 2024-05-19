package com.jms.producer.demo.dao;

import com.jms.producer.demo.dao.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

