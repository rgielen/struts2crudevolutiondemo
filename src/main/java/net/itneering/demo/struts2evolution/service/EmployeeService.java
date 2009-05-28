package net.itneering.demo.struts2evolution.service;

import net.itneering.demo.struts2evolution.model.Employee;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * EmployeeService.
 *
 * @author Rene Gielen
 */
public class EmployeeService extends GenericEntityService<Employee, Long> {

    public Class<Employee> entityClass() {
        return Employee.class;
    }

    @Transactional
    public List<Employee> getEmployeeByName( String name ) {
        return getCurrentSession().getNamedQuery("Employee.getByName")
                .setString("name", name)
                .list();
    }

}
