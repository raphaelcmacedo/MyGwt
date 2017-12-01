package com.mygwt.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.mygwt.client.rpc.EmployeeServiceClientImpl;
import com.mygwt.shared.entity.Employee;
import com.mygwt.shared.entity.Site;

public class EmployeeList extends Composite {

	private static EmployeeListUiBinder uiBinder = GWT
			.create(EmployeeListUiBinder.class);

	interface EmployeeListUiBinder extends UiBinder<Widget, EmployeeList> {
	}

	public EmployeeList(EmployeeServiceClientImpl service) {
		initWidget(uiBinder.createAndBindUi(this));
		this.service = service;
		this.configureForm();

	}

	private EmployeeServiceClientImpl service;
	private Employee employee = new Employee();

	// UIComponents
	@UiField
	CellTable<Employee> table;

	@UiField
	Button btnClear;
	@UiField
	Button btnSave;
	@UiField
	Button btnDelete;
	
	@UiField
	TextBox txtId;
	@UiField
	TextBox txtName;
	@UiField
	ListBox txtSite;
	@UiField
	TextBox txtPosition;
	
	private void configureForm() {
		table.setAutoHeaderRefreshDisabled(true);

		this.configureTableColumns();
		this.configureSelectionHandler();

		// Call the services to load the screen
		service.listAll();
		service.listSites();
	}

	private void configureTableColumns() {
		// Name
		TextColumn<Employee> nameColumn = new TextColumn<Employee>() {
			@Override
			public String getValue(Employee object) {
				return object.getName();
			}
		};
		table.addColumn(nameColumn, "Name");

		// Site
		TextColumn<Employee> siteColumn = new TextColumn<Employee>() {
			@Override
			public String getValue(Employee object) {
				return object.getSite().getName();
			}
		};
		table.addColumn(siteColumn, "Site");

		// Site
		TextColumn<Employee> positionColumn = new TextColumn<Employee>() {
			@Override
			public String getValue(Employee object) {
				return object.getPosition();
			}
		};
		table.addColumn(positionColumn, "Positiion");
	}

	private void configureSelectionHandler() {
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		final SingleSelectionModel<Employee> selectionModel = new SingleSelectionModel<Employee>();
		table.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						Employee selected = selectionModel.getSelectedObject();
						if (selected != null) {
							// Window.alert("You selected: " +
							// selected.getName());
							service.findById(selected.getId());
						}
					}
				});
	}
	
	private void setObject(){
		employee.setName(txtName.getText());
		employee.setPosition(txtPosition.getText());
		employee.setSite(this.getSelectedSite());
	}
	
	@UiHandler("btnClear")
	void onClickClear(ClickEvent e) {
		this.clear();
	}
	
	public void clear(){
		employee = new Employee();
		txtId.setText("");
		txtName.setText("");
		txtSite.setSelectedIndex(0);
		txtPosition.setText("");
	}
	
	@UiHandler("btnSave")
	void onClickSave(ClickEvent e) {
		setObject();
		service.save(employee);
	}
	
	@UiHandler("btnDelete")
	void onClickDelete(ClickEvent e) {
		setObject();
		if(txtId.getText().isEmpty()){
			Window.alert("Please select an entry to delete");
			return;
		}
		service.delete(employee.getId());
	}
	
	public void fillTable(List<Employee> employees) {
		table.setRowCount(employees.size(), true);
		table.setRowData(0, employees);
	}
	
	public void fillEmployee(Employee employee) {
		this.employee = employee;
		txtId.setText(employee.getId() + "");
		txtName.setText(employee.getName());
		txtPosition.setText(employee.getPosition());
		this.setSelectedSite(employee.getSite());
	}
	
	public void fillListBox(List<Site> sites){
		txtSite.clear();
		for(Site site : sites){
			txtSite.addItem(site.getName(), site.getId()+"");	
		}
		
		
	}
	
	private Site getSelectedSite(){
		String value = txtSite.getSelectedValue();
		Site site = new Site();
		site.setId(Integer.parseInt(value));
		
		return site;	
	}
	
	private void setSelectedSite(Site site){
		for (int i = 0; i < txtSite.getItemCount(); i++) {
			String currentName = txtSite.getItemText(i);
			if(site.getName().equals(currentName)){
				txtSite.setSelectedIndex(i);
			}
		}
	}
}
