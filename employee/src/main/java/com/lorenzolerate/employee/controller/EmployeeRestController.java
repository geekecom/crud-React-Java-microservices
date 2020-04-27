package com.lorenzolerate.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.lorenzolerate.employee.EmployeeRepo;
import com.lorenzolerate.employee.TechnologyRepo;
import com.lorenzolerate.employee.model.Employee;
import com.lorenzolerate.employee.model.Technology;

@RestController
@RequestMapping("rest")
public class EmployeeRestController {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    TechnologyRepo technologyRepo;

    @RequestMapping()
    public List<Employee> showAllEmployees() {
	List<Employee> allEmployees = employeeRepo.findAll();
	return allEmployees;
    }

    @RequestMapping("/delete/{id}")
    public void deleteEmploye(@PathVariable Integer id) {
	employeeRepo.deleteById(id);
    }

    @RequestMapping(path = "/addEmployee/{employeeName}/{technologyName}")
    public void addEmployee(@PathVariable String employeeName, @PathVariable String technologyName) {
	Employee employeeToAdd = new Employee();

	employeeToAdd.setName(employeeName);

	Technology technology;
	if (technologyRepo.existsByName(technologyName)) {
	    technology = technologyRepo.findByName(technologyName);
	    employeeToAdd.setTechnology(technology);
	} else {
	    technology = new Technology(technologyName);
	    technologyRepo.save(technology);
	}
	employeeToAdd.setTechnology(technology);

	employeeRepo.save(employeeToAdd);
    }

    @RequestMapping(path = "/editEmployee/{id}/{employeeName}/{technologyName}")
    public void editEmployee(@PathVariable Integer id,@PathVariable String employeeName, @PathVariable String technologyName) {
	Employee employeeToEdit = employeeRepo.getOne(id);

	employeeToEdit.setName(employeeName);

	Technology technology;
	if (technologyRepo.existsByName(technologyName)) {
	    technology = technologyRepo.findByName(technologyName);
	    employeeToEdit.setTechnology(technology);
	} else {
	    technology = new Technology(technologyName);
	}
	employeeToEdit.setTechnology(technology);

	employeeRepo.save(employeeToEdit);
    }

}
