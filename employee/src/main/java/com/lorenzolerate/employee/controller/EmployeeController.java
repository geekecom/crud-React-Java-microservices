package com.lorenzolerate.employee.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.lorenzolerate.employee.EmployeeRepo;
import com.lorenzolerate.employee.TechnologyRepo;
import com.lorenzolerate.employee.model.Employee;
import com.lorenzolerate.employee.model.Technology;

@Controller
public class EmployeeController {

  @Autowired
  EmployeeRepo employeeRepo;

  @Autowired
  TechnologyRepo technologyRepo;

  @RequestMapping("/")
  public String showEmployees(Model model) {
    List<Employee> allEmployees = employeeRepo.findAll();
    model.addAttribute("allEmployees", allEmployees);
    return "employees.jsp";
  }

  @RequestMapping("/addEmployee")
  public String addEmployee(Model model) {
    model.addAttribute("newEmployee", new Employee());
    return "addEmployee.jsp";
  }


  @PostMapping(path = "/addEmployee")
  public RedirectView addEmployee(@ModelAttribute("newEmployee") Employee employee) {
    String technologyName = employee.getTechnology().getName();
    if (technologyRepo.existsByName(technologyName)) {
      Technology technology = technologyRepo.findByName(technologyName);
      employee.setTechnology(technology);
    }
    employeeRepo.save(employee);
    return new RedirectView("/employee");
  }
  
  @RequestMapping("/removeEmployee")
  public String removeEmployee(Model model) {
    List<Employee> allEmployees = employeeRepo.findAll();
    Employee employeeToRemove = new Employee();
    model.addAttribute("allEmployees", allEmployees);
    model.addAttribute("employeeToRemove", employeeToRemove);
    return "removeEmployee.jsp";
  }
  
  @PostMapping(path = "/removeEmployee")
  public RedirectView removeEmployee(@ModelAttribute("employeeToRemove") Employee employee) {
    employeeRepo.delete(employee);  
    return new RedirectView("/employee");
  }
}
