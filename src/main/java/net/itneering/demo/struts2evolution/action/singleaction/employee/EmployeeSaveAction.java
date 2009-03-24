package net.itneering.demo.struts2evolution.action.singleaction.employee;

import net.itneering.demo.struts2evolution.action.AbstractCRUDAction;
import net.itneering.demo.struts2evolution.model.Employee;
import net.itneering.demo.struts2evolution.service.EmployeeService;

/**
 * EmployeeSaveAction (Class per Action - Paradigma).
 *
 * @author Rene Gielen
 */
public class EmployeeSaveAction extends AbstractCRUDAction {

    EmployeeService employeeService;
    Employee employee;

    public void setEmployeeService( EmployeeService employeeService ) {
        this.employeeService = employeeService;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee( Employee employee ) {
        this.employee = employee;
    }

    public String execute() throws Exception {
        employeeService.saveOrUpdate(employee);
        return SUCCESS;
    }
}
