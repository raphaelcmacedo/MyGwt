package com.mygwt.shared.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name="List")
public class Employees implements Serializable {
	private List<Employee> list = new ArrayList<Employee>();
	
	public Employees() {
	
	}
	
	public Employees(List<Employee> list) {
		this.list = list;
	}
	
	@XmlElement(name="Employee")
	public List<Employee> getList() {
		return list;
	}

	public void setList(List<Employee> list) {
		this.list = list;
	}
	
}
