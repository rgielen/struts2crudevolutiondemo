package net.itneering.demo.struts2evolution.action.singleaction.employee;

import com.opensymphony.xwork2.ActionSupport;
import net.itneering.demo.struts2evolution.model.Employee;
import net.itneering.demo.struts2evolution.service.EmployeeService;

/**
 * EmployeeEditAction (Class per Action - Paradigma)
 *
 * @author Rene Gielen
 */
public class EmployeeEditAction extends ActionSupport {

    EmployeeService employeeService;
    Employee employee;
    String foo;

    public void setEmployeeService( EmployeeService employeeService ) {
        this.employeeService = employeeService;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee( Employee employee ) {
        this.employee = employee;
    }

    public String getFoo() {
        return foo;
    }

    public void setFoo( String foo ) {
        this.foo = foo;
    }

    public String execute() throws Exception {
        if (employee != null && employee.getId() != null) {
        	this.employee = employeeService.get(employee.getId());
        }
        return SUCCESS;
    }
}
