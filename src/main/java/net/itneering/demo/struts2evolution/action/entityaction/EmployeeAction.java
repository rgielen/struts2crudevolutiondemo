package net.itneering.demo.struts2evolution.action.entityaction;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import net.itneering.demo.struts2evolution.model.Employee;
import net.itneering.demo.struts2evolution.service.EmployeeService;
import net.itneering.demo.struts2evolution.action.AbstractCRUDAction;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 * EmployeeAction (Class per Modelentity Actions). Mit Annotation-basierter Validierung, ModelDriven. Wird durch die
 * Generische Konfiguration in struts.xml gematcht.
 *
 * @author Rene Gielen
 */
public class EmployeeAction extends AbstractCRUDAction implements Preparable, ModelDriven {

    EmployeeService employeeService;
    Employee employee = new Employee(); // Für ModelDriven sollte die Initialisierung stattfinden

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

    public String save() throws Exception {
        employeeService.saveOrUpdate(employee);
        return SUCCESS;
    }

    /**
     * Statt execute eine etwas "sprechendere" edit Methode. Validierung darf für die Edit-Action nicht stattfinden, da
     * sonst wäre die Neuanlage nicht möglich.
     *
     * @return immer {@link #SUCCESS}
     *
     * @throws Exception
     */
    @SkipValidation
    public String edit() throws Exception {
        return SUCCESS;
    }

}