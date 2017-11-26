package com.mygwt.client.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mygwt.shared.entity.Employee;

public interface EmployeeServiceClient {

	void delete(int id);
	void findById(int id);
	void listAll();
	void save(Employee employee);

}
