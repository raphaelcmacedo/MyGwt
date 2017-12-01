package com.mygwt.client.rpc;

import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mygwt.client.EmployeeList;
import com.mygwt.shared.entity.Employee;
import com.mygwt.shared.entity.Site;

public class EmployeeServiceClientImpl implements EmployeeServiceClient {

	private EmployeeServiceAsync service;
	private EmployeeCallback employeeCallback;
	private SiteCallback siteCallback;
	private SaveCallback saveCallback;
	private EmployeeList employeeList;
	
	public EmployeeServiceClientImpl(String url) {
		this.service = GWT.create(EmployeeService.class);
		/*ServiceDefTarget endpoint = (ServiceDefTarget)this.service;
		endpoint.setServiceEntryPoint(url);*/
		this.employeeCallback = new EmployeeCallback();
		this.siteCallback = new SiteCallback();
		this.saveCallback = new SaveCallback();
		this.employeeList = new EmployeeList(this);
	}

	public EmployeeList getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(EmployeeList employeeList) {
		this.employeeList = employeeList;
	}

	@Override
	public void delete(int id) {
		this.service.delete(id, saveCallback);
	}

	@Override
	public void findById(int id) {
		this.service.findById(id, employeeCallback);
		
	}

	@Override
	public void listAll() {
		this.service.listAll(employeeCallback);
	}
	
	@Override
	public void listSites() {
		this.service.listSites(siteCallback);
	}

	@Override
	public void save(Employee employee) {
		this.service.save(employee, saveCallback);
		
	}
	
	private class EmployeeCallback implements AsyncCallback{

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
				employeeList.fillEmployee(employee);
			}
		}
	}
	
	private class SaveCallback implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("failure");
			
		}

		@Override
		public void onSuccess(Object result) {
			System.out.println("success");
			listAll();
			employeeList.clear();
		}
	}
	
	private class SiteCallback implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("failure");
			
		}

		@Override
		public void onSuccess(Object result) {
			System.out.println("success");
			if (result instanceof List) {
				List<Site> sites = (List<Site>) result;
				employeeList.fillListBox(sites);
			}
		}
	}
	
}
