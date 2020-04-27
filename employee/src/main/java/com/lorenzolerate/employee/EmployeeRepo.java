package com.lorenzolerate.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.lorenzolerate.employee.model.Employee;

@RepositoryRestResource(collectionResourceRel="employees",path="employees")
public interface EmployeeRepo extends JpaRepository<Employee,Integer>{

    void deleteEmployeeById(Integer id);

}
