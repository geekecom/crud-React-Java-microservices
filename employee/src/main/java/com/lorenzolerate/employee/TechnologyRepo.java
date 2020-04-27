package com.lorenzolerate.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lorenzolerate.employee.model.Technology;

public interface TechnologyRepo extends JpaRepository<Technology,Integer>{

  public boolean existsByName(String name);

  public Technology findByName(String name);  
}
