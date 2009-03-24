package net.itneering.demo.struts2evolution.model;

import javax.persistence.Id;
import javax.persistence.Entity;

/**
 * Skill.
 *
 * @author Rene Gielen
 */
@Entity
public class Skill {

    String key;
    String anzeigeName;

    @Id
    public String getKey() {
        return key;
    }

    public void setKey( String key ) {
        this.key = key;
    }

    public String getAnzeigeName() {
        return anzeigeName;
    }

    public void setAnzeigeName( String anzeigeName ) {
        this.anzeigeName = anzeigeName;
    }
}
