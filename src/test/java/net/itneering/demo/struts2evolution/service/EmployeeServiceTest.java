package net.itneering.demo.struts2evolution.service;

import net.itneering.demo.struts2evolution.AbstractModelServiceSpringContextTest;
import net.itneering.demo.struts2evolution.model.Employee;

/**
 * EmployeeServiceTest test for given class.
 *
 * @author <a href="mailto:gielen@it-neering.net">Rene Gielen</a>
 */

public class EmployeeServiceTest extends AbstractModelServiceSpringContextTest {

    EmployeeService employeeService;

    protected void onSetUp() throws Exception {
        super.onSetUp();
        this.employeeService = (EmployeeService) getApplicationContext().getBean("employeeService");
    }


    public void testServiceAvailable() throws Exception {
        assertNotNull(employeeService);
    }

    public void testSaveAndGet() throws Exception {
        startTransaction();
        try {
            Long id = employeeService.save(new Employee("foo", "bar", "foobar"));
            Employee emp  = employeeService.get(id);
            assertNotNull(emp);
            assertEquals("foobar", emp.getBenutzername());
        } finally {
            rollback();
        }
    }


}