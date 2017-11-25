package com.mygwt.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.mygwt.shared.entity.Employee;

public class EmployeeList extends Composite {

	private static EmployeeListUiBinder uiBinder = GWT
			.create(EmployeeListUiBinder.class);

	interface EmployeeListUiBinder extends UiBinder<Widget, EmployeeList> {
	}

	public EmployeeList() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public EmployeeList(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void fillTable(List<Employee> employees) {
		DataGrid<Employee> table = new DataGrid<Employee>();
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		this.configureTableColumns(table);
		this.configureSelectionHandler(table);

		table.setRowCount(employees.size(), true);
		table.setRowData(0, employees);
		table.setWidth("100%");
		SimpleLayoutPanel slp = new SimpleLayoutPanel();
		slp.add(table);
		RootLayoutPanel.get().add(slp);

	}

	private void configureTableColumns(DataGrid<Employee> table) {
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

	private void configureSelectionHandler(DataGrid<Employee> table) {
		final SingleSelectionModel<Employee> selectionModel = new SingleSelectionModel<Employee>();
		table.setSelectionModel(selectionModel);
		selectionModel
				.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						Employee selected = selectionModel.getSelectedObject();
						if (selected != null) {
							Window.alert("You selected: "
									+ selected.getName());
						}
					}
				});
	}

}
