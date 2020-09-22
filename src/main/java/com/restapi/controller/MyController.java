package com.restapi.controller;

import java.time.LocalDate;
import java.util.List;
//import java.util.Optional;

import com.restapi.model.Patient;
import com.restapi.service.MyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private MyService myService;

    @GetMapping("/patient/list")
    public List<Patient> listAll() {
        return myService.listAll();
    }


    @GetMapping("/patient")
    public List<Patient> searchPatient(@RequestParam(value = "firstname") String name) {
        return myService.getByFnameAndLname(name);
    }
/*
     @GetMapping("/patient")
    public Optional<Patient> searchPatient(@RequestParam(value = "pid") String pid) {
        return myService.getByPid(pid);
    }
*/

    @PostMapping("/patient")
    public Patient newPatient(@RequestBody Patient patient){
        String pattern = "^([A-Za-z0-9])*$";
       
        if (patient.getFirstname().matches(pattern) && patient.getLastname().matches(pattern) && patient.getDob().isBefore(LocalDate.now()) ) {
           System.out.println("valid");
            return myService.saveOrUpdate(patient);
        } else {
                System.out.println("Input format should be [A-Za-z0-9]");
                return null;
        }
    }


     /*@PostMapping("/patient")
    public Patient newPatient(@RequestBody Patient patient){
           return myService.saveOrUpdate(patient);
    }*/

    
}