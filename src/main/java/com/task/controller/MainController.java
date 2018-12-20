package com.task.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.task.model.Employee;
import com.task.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;


@Controller
public class MainController {

    private EmployeeRepository employeeRepository;

    @Value("${employees_photo_url}")
    private String employeesPhotoUrl;

    @Autowired
    public MainController(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }


    @GetMapping
    public String mainPage(Model model){
        showAllEmployees(model);
        return "index";
    }

    @PostMapping("/add_employee")
    public String addEmployee(@RequestParam(name = "emp_full_name") String fullName,
                              @RequestParam(name = "emp_age") Long age,
                              @RequestParam(name = "emp_job_title") String jobTitle,
                              @RequestParam(name = "emp_home_city") String homeCity,
                              @RequestParam(name = "experience") String experience,
                              @RequestParam(name = "emp_photo") MultipartFile multipartPhoto) throws IOException {
        Employee employee = new Employee(fullName, age, jobTitle, homeCity, experience);
        String photoUrl = employeesPhotoUrl + "/" + multipartPhoto.getOriginalFilename();
        employee.setPhotoUrl(photoUrl);
        System.out.println(photoUrl);
        File file = multipartToFile(multipartPhoto);
        employeeRepository.save(employee);
        return "redirect:index";
    }

    @GetMapping("employee/{id}")
    public String employeeRecord(@PathVariable(name = "id") Long id, Model model){
        Optional<Employee> employeeOpt = employeeRepository.findById(id);
        if (employeeOpt.isPresent()){
            Employee employee = employeeOpt.get();
            model.addAttribute("employee", employee);
        }
        return "employee";
    }

    @GetMapping("/delete_all")
    public String deleteAll(){
        employeeRepository.deleteAll();
        return "redirect:index";
    }

    private File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        if (multipart == null) throw new NullPointerException();
        File convFile = new File(employeesPhotoUrl + "/" + multipart.getOriginalFilename());
        multipart.transferTo(convFile);
        return convFile;
    }

    private void showAllEmployees(Model model) {
        List<Employee> allEmployee =  employeeRepository.findAll();
        model.addAttribute("allEmployees", allEmployee);
    }


}
