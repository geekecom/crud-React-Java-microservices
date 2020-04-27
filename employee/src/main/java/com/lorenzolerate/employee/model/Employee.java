package com.lorenzolerate.employee.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne()
    @JoinColumn(name = "technology_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Technology technology;

    public Employee() {
	super();
    }

    public Employee(String name, Technology technology) {
	super();
	this.name = name;
	this.technology = technology;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Technology getTechnology() {
	return technology;
    }

    public void setTechnology(Technology technology) {
	this.technology = technology;
    }

}
