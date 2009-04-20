package net.itneering.demo.struts2evolution.model;

import java.io.Serializable;

/**
 * IdHavingEntity. Interface.
 *
 * @author Rene Gielen
 */

public interface IdHavingEntity <T extends Serializable> {

    T getId();

    void setId( T id );
}
