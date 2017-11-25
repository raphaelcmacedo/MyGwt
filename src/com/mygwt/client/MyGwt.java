package com.mygwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.mygwt.client.rpc.EmployeeServiceClientImpl;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MyGwt implements EntryPoint {
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		EmployeeServiceClientImpl service = new EmployeeServiceClientImpl(GWT.getModuleBaseURL() + "myservice");
		RootPanel.get().add(service.getEmployeeList());
	}
}
