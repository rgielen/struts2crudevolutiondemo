package net.itneering.demo.struts2evolution.action.singleaction.employee;

import net.itneering.demo.struts2evolution.service.EmployeeService;
import net.itneering.demo.struts2evolution.model.Employee;
import net.itneering.demo.struts2evolution.action.AbstractCRUDAction;
import com.opensymphony.xwork2.Preparable;

/**
 * EmployeeAction (Class per Modelentity Actions).
 * Ohne Validierung, nicht ModelDriven.
 *
 * @author Rene Gielen
 */
public class EmployeeAction extends AbstractCRUDAction implements Preparable {

    EmployeeService employeeService;
    Employee employee;

    /**
     * Wird durch Spring respektive Strut2-Spring-Integration per Property-Name injeziert
     *
     * @param employeeService
     */
    public void setEmployeeService( EmployeeService employeeService ) {
        this.employeeService = employeeService;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee( Employee employee ) {
        this.employee = employee;
    }

    public void prepare() throws Exception {
        if (employee != null && employee.getId() != null) {
        	this.employee = employeeService.get(employee.getId());
        }
    }

    public String save() throws Exception {
        employeeService.saveOrUpdate(employee);
        return SUCCESS;
    }

}
