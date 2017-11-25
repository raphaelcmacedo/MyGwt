package com.mygwt.server.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXB;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mygwt.client.rpc.EmployeeService;
import com.mygwt.shared.entity.Employee;
import com.mygwt.shared.entity.Employees;

public class EmployeServiceImpl extends RemoteServiceServlet implements EmployeeService {

	private static String BASE_URL = "http://localhost:8081/api/employee";
	
	private HttpURLConnection createConnection(URL url, String method) throws IOException{
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(method);
		connection.setRequestProperty("Accept", "text/xml");
		connection.setRequestProperty("Content-Type", "text/xml");
		connection.setDoOutput(true);
		
		return connection;
	}
	
	@Override
	public List<Employee> listAll() {
		try{
			URL url = new URL(BASE_URL);
			InputStream input = url.openStream();
			List<Employee> employees = JAXB.unmarshal(new InputStreamReader(input), Employees.class).getList();
			return employees;	
		}catch (IOException ioe){
			System.out.println("It was not possible to stablish the connection");
			return null;
		}
		
	}

	@Override
	public Employee findById(int id) {
		try{
			URL url = new URL(BASE_URL + "/" + id);
			InputStream input = url.openStream();
			Employee employee = JAXB.unmarshal(new InputStreamReader(input), Employee.class);
			return employee;	
		}catch (IOException ioe){
			System.out.println("It was not possible to stablish the connection");
			return null;
		}
		
	}

	@Override
	public Employee save(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
