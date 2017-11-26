package com.mygwt.client.rpc;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.mygwt.client.EmployeeList;
import com.mygwt.shared.entity.Employee;

public class EmployeeServiceClientImpl implements EmployeeServiceClient {

	private EmployeeServiceAsync service;
	private GenericCallback genericCallback;
	private EmployeeList employeeList;
	
	public EmployeeServiceClientImpl(String url) {
		this.service = GWT.create(EmployeeService.class);
		/*ServiceDefTarget endpoint = (ServiceDefTarget)this.service;
		endpoint.setServiceEntryPoint(url);*/
		this.genericCallback = new GenericCallback();
		this.employeeList = new EmployeeList(this);
		
		//List the employees
		this.listAll();
	}

	public EmployeeList getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(EmployeeList employeeList) {
		this.employeeList = employeeList;
	}

	@Override
	public void delete(int id) {
		this.service.delete(id, genericCallback);
	}

	@Override
	public void findById(int id) {
		this.service.findById(id, genericCallback);
		
	}

	@Override
	public void listAll() {
		this.service.listAll(genericCallback);
		
	}

	@Override
	public void save(Employee employee) {
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
			} else if (result instanceof Employee) {
				Employee employee = (Employee) result;
				
			}
		}
		
		
		
	}
	
}
