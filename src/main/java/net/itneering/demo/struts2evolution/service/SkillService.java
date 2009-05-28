package net.itneering.demo.struts2evolution.service;

import net.itneering.demo.struts2evolution.model.Employee;
import net.itneering.demo.struts2evolution.model.Skill;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * EmployeeService.
 *
 * @author Rene Gielen
 */
public class SkillService extends GenericEntityService<Skill, String> {

    public Class<Skill> entityClass() {
        return Skill.class;
    }

}