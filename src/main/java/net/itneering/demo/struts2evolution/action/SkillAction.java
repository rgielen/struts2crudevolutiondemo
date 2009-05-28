package net.itneering.demo.struts2evolution.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;
import net.itneering.demo.struts2evolution.model.Skill;
import net.itneering.demo.struts2evolution.service.SkillService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.util.List;

@InterceptorRefs({
        @InterceptorRef("transactionInterceptor"),
        @InterceptorRef("paramsPrepareParamsStack")
})
public class SkillAction extends ActionSupport implements Preparable, ModelDriven {

    SkillService skillService;

    Skill skill = new Skill();
    private List<Skill> list;
    private int skillHash;

    /**
     * will be injected
     */
    public void setSkillService( SkillService skillService ) {
        this.skillService = skillService;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill( Skill skill ) {
        this.skill = skill;
    }

    public List<Skill> getList() {
        return list;
    }

    @VisitorFieldValidator(message = "", appendPrefix = false)
    public Object getModel() {
        return skill;
    }

    public void prepare() throws Exception {
        if (skill != null && skill.getKey() != null) {
            this.skill = skillService.get(skill.getKey());
            this.skillHash = skill.hashCode();
        }
    }

    @SkipValidation
    @Action(value = "skill",
            results = {@Result(name = "success", type = "freemarker", location = "skill.ftl")}
    )
    public String execute() throws Exception {
        return SUCCESS;
    }

    @Action(value = "skills",
            results = {@Result(name = "success", type = "freemarker", location = "skill-list.ftl")}
    )
    @SkipValidation
    public String list() throws Exception {
        this.list = skillService.findAll();
        return SUCCESS;
    }

    @Action(value = "saveSkill",
            results = {
                    @Result(name = "success", location = "skill", type = "redirectAction",
                            params = {"model.key", "${key}"}),
                    @Result(name = "input", type = "freemarker", location = "skill.ftl")
            }
    )
    public String save() throws Exception {
        if (skill.hashCode() != skillHash) {
            skillService.saveOrUpdate(skill);
        }
        return SUCCESS;
    }

}