package net.itneering.demo.struts2evolution.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import net.itneering.demo.struts2evolution.model.Employee;
import net.itneering.demo.struts2evolution.model.Skill;
import net.itneering.demo.struts2evolution.service.EmployeeService;
import net.itneering.demo.struts2evolution.service.SkillService;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;

import java.util.List;

@InterceptorRefs({
        @InterceptorRef("transactionInterceptor"),
        @InterceptorRef("paramsPrepareParamsStack")
})
public class EmployeeAction extends ActionSupport implements Preparable, ModelDriven {

    EmployeeService employeeService;
    SkillService skillService;

    Employee employee = new Employee();
    private List<Employee> list;
    private int empHash;

    /**
     * will be injected
     */
    public void setEmployeeService( EmployeeService employeeService ) {
        this.employeeService = employeeService;
    }

    /**
     * will be injected
     */
    public void setSkillService( SkillService skillService ) {
        this.skillService = skillService;
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
            this.empHash = employee.hashCode();
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
                            params = {"model.id", "${id}"}),
                    @Result(name = "input", location = "employee.jsp")
            }
    )
    public String save() throws Exception {
        if (employee.hashCode() != empHash) {
            employeeService.saveOrUpdate(employee);
        }
        return SUCCESS;
    }

    public List<Skill> getSkills() {
        return skillService.findAll();   
    }

}