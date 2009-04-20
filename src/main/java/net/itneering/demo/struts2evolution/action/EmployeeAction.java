package net.itneering.demo.struts2evolution.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import net.itneering.demo.struts2evolution.model.Employee;
import net.itneering.demo.struts2evolution.model.IdHavingEntity;
import net.itneering.demo.struts2evolution.service.EmployeeService;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;

import java.util.List;

@InterceptorRefs({
    @InterceptorRef("paramsPrepareParamsStack")
})
public class EmployeeAction extends ActionSupport implements Preparable, ModelDriven {

    EmployeeService employeeService;
    Employee employee = new Employee(); // Für ModelDriven sollte die Initialisierung stattfinden
    private List<Employee> list;

    /**
     * will be injected
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

    public List<Employee> getList() {
        return list;
    }

    @VisitorFieldValidator(message = "", appendPrefix = false)
    public Object getModel() {
        return employee;
    }

    public void prepare() throws Exception {
        if (employee != null && employee.getId() != null) {
            this.employee = employeeService.get(employee.getId());
        }
    }

    @SkipValidation
    public String execute() throws Exception {
        return SUCCESS;
    }

    @Action(value = "employees",
            results = {@Result(name = "success", location = "employee-list.jsp")}
    )
    @SkipValidation
    public String list() throws Exception {
        this.list = employeeService.findAll();
        return SUCCESS;
    }

    @Action(value = "saveEmployee",
            results = {
                    @Result(name = "success", location = "employee", type = "redirectAction",
                            params={"model.id", "${id}"}),
                    @Result(name = "input", location = "employee.jsp")
            }
    )
    public String save() throws Exception {
        employeeService.saveOrUpdate(employee);
        return SUCCESS;
    }

}