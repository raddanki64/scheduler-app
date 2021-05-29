package com.apps.schedulerApp.persistance;

import  com.apps.schedulerApp.persistance.model.Employee;

import  org.slf4j.Logger;
import  org.slf4j.LoggerFactory;
import  org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Service;

@Service
public class SqlServerServiceImpl implements SqlServerService {
    private static Logger LOG = LoggerFactory.getLogger(SqlServerServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public SqlServerServiceImpl() {
    }

    @Override
    public void process() throws Exception {
        Iterable<Employee> employees = employeeRepository.findAll();

        String output = "";
        for (Employee emp : employees) {
            output += emp + "\n";
        }

        LOG.info(output);
    }
}