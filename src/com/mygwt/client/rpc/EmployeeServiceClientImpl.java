package com.mygwt.client.rpc;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mygwt.client.EmployeeList;
import com.mygwt.shared.entity.Employee;

public class EmployeeServiceClientImpl implements EmployeeServiceClient {

	private EmployeeServiceAsync service;
	private GenericCallback genericCallback;
	private EmployeeList employeeList;
	
	public EmployeeServiceClientImpl(String url) {
		this.service = GWT.create(EmployeeService.class);
		this.genericCallback = new GenericCallback();
		this.employeeList = new EmployeeList();
		
		//List the employees
		this.listAll(genericCallback);
	}

	public EmployeeList getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(EmployeeList employeeList) {
		this.employeeList = employeeList;
	}

	@Override
	public void delete(int id, AsyncCallback<Void> callback) {
		this.service.delete(id, genericCallback);
	}

	@Override
	public void findById(int id, AsyncCallback<Employee> callback) {
		this.service.findById(id, genericCallback);
		
	}

	@Override
	public void listAll(AsyncCallback<List<Employee>> callback) {
		System.out.println("chamou lista");
		this.service.listAll(genericCallback);
		
	}

	@Override
	public void save(Employee employee, AsyncCallback<Employee> callback) {
		this.service.save(employee, genericCallback);
		
	}
	
	private class GenericCallback implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("failure");
			
		}

		@Override
		public void onSuccess(Object result) {
			System.out.println("success");
			if (result instanceof List) {
				List<Employee> employees = (List<Employee>) result;
				employeeList.fillTable(employees);
				
			}
		}
		
		
		
	}
	
}
