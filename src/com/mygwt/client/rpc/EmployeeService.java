package com.mygwt.client.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.mygwt.shared.entity.Employee;
import com.mygwt.shared.entity.Site;

@RemoteServiceRelativePath("employeeService")
public interface EmployeeService extends RemoteService {
	List<Employee> listAll();
	List<Site> listSites();
	Employee findById(int id);
	Employee save(Employee employee);
	void delete (int id);
}
