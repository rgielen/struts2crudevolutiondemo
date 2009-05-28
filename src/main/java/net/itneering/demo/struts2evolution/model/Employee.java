package net.itneering.demo.struts2evolution.model;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Employee.
 *
 * @author Rene Gielen
 */
@Entity()
@SequenceGenerator(name = "Employee_SEQ", sequenceName = "Employee_SEQ", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = "Employee.getByName",
                query = "select anrede,nachname from Employee emp where benutzername=:name")
})
public class Employee implements IdHavingEntity<Long> {

    Long id;
    String anrede;
    String nachname;
    String vorname;
    String benutzername;
    String passwort;
    Integer jahreslohn;
    String abteilung;
    Skill hauptSkill;
    String email;
    Date geburtsDatum;
    List<Skill> skills;

    public Employee() {
    }

    public Employee( String vorname, String nachname, String benutzername ) {
        this.nachname = nachname;
        this.vorname = vorname;
        this.benutzername = benutzername;
    }

    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Employee_SEQ")
    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getAnrede() {
        return anrede;
    }

    public void setAnrede( String anrede ) {
        this.anrede = anrede;
    }

    @RequiredStringValidator(message = "Eingabe benötigt", key = "validation.required")
    public String getNachname() {
        return nachname;
    }

    public void setNachname( String nachname ) {
        this.nachname = nachname;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname( String vorname ) {
        this.vorname = vorname;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername( String benutzername ) {
        this.benutzername = benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort( String passwort ) {
        this.passwort = passwort;
    }

    public Integer getJahreslohn() {
        return jahreslohn;
    }

    public void setJahreslohn( Integer jahreslohn ) {
        this.jahreslohn = jahreslohn;
    }

    public String getAbteilung() {
        return abteilung;
    }

    public void setAbteilung( String abteilung ) {
        this.abteilung = abteilung;
    }

    @ManyToOne
    public Skill getHauptSkill() {
        return hauptSkill;
    }

    public void setHauptSkill( Skill hauptSkill ) {
        this.hauptSkill = hauptSkill;
    }

    @EmailValidator(message = "", key = "validation.email")
    @RequiredStringValidator(message = "Eingabe benötigt", key = "validation.required")
    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    @Temporal(TemporalType.DATE)
    public Date getGeburtsDatum() {
        return geburtsDatum;
    }

    public void setGeburtsDatum( Date geburtsDatum ) {
        this.geburtsDatum = geburtsDatum;
    }

}
