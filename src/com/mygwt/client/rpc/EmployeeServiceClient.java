package com.mygwt.client.rpc;

import com.mygwt.shared.entity.Employee;

public interface EmployeeServiceClient {

	void delete(int id);
	void findById(int id);
	void listAll();
	void listSites();
	void save(Employee employee);

}
