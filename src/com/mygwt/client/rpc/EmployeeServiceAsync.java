package com.mygwt.client.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mygwt.shared.entity.Employee;
import com.mygwt.shared.entity.Site;

public interface EmployeeServiceAsync {

	void delete(int id, AsyncCallback<Void> callback);

	void findById(int id, AsyncCallback<Employee> callback);

	void listAll(AsyncCallback<List<Employee>> callback);

	void save(Employee employee, AsyncCallback<Employee> callback);

	void listSites(AsyncCallback<List<Site>> callback);

}
