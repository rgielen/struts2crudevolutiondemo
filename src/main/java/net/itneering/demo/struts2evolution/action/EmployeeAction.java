package net.itneering.demo.struts2evolution.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import net.itneering.demo.struts2evolution.model.Employee;
import net.itneering.demo.struts2evolution.service.EmployeeService;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;

@InterceptorRefs({
    @InterceptorRef("paramsPrepareParamsStack")
})
public class EmployeeAction extends ActionSupport implements Preparable, ModelDriven {

    EmployeeService employeeService;
    Employee employee = new Employee(); // Für ModelDriven sollte die Initialisierung stattfinden

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

    /**
     * Implementierung des Modeldriven-Paradigmas. Ausserdem Validierung durch Visitor, d.h. entnehme die
     * Validierungs-Regeln der Modelklasse {@link Employee}.
     *
     * @return
     */
    @VisitorFieldValidator(message = "", appendPrefix = false)
    public Object getModel() {
        return employee;
    }

    public void prepare() throws Exception {
        if (employee != null && employee.getId() != null) {
            this.employee = employeeService.get(employee.getId());
        }
    }

    @Action(value = "saveEmployee",
            results = {
                    @Result(name = "success", location = "employee.action", type = "redirectAction",
                            params={"model.id", "${id}"}),
                    @Result(name = "input", location = "employee.jsp")
            }
    )
    public String save() throws Exception {
        employeeService.saveOrUpdate(employee);
        return SUCCESS;
    }

    @SkipValidation
    public String execute() throws Exception {
        return SUCCESS;
    }

}