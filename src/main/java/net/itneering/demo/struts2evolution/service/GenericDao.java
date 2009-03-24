package net.itneering.demo.struts2evolution.service;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * GenericDao.
 *
 * @author Rene Gielen
 */
@Repository
public class GenericDao extends HibernateDaoSupport implements InitializingBean {

    private boolean allowCreateNew = true;

    public void setAllowCreateNew( boolean allowCreateNew ) {
        this.allowCreateNew = allowCreateNew;
    }

    public boolean isAllowCreateNew() {
        return allowCreateNew;
    }

    public Session getCurrentSession() {
        return getSession(isAllowCreateNew());
    }

    public Serializable save( final Object modelObject ) throws DataAccessException {
        return getCurrentSession().save(modelObject);
    }

    public void update( final Object modelObject ) throws DataAccessException {
        getCurrentSession().update(modelObject);
    }

    public void merge( final Object modelObject ) throws DataAccessException {
        getCurrentSession().merge(modelObject);
    }

    public void saveOrUpdate( final Object modelObject ) throws DataAccessException {
        getCurrentSession().saveOrUpdate(modelObject);
    }

    public Object load( final Class clazz, final Long id ) throws DataAccessException {
        return getCurrentSession().load(clazz, id);
    }

    public Object get( final Class clazz, final Long id ) throws DataAccessException {
        return getCurrentSession().get(clazz, id);
    }

    public void delete( final Object modelObject ) throws DataAccessException {
        getCurrentSession().delete(modelObject);
    }

    public Query createQuery( String queryString ) {
        return getCurrentSession().createQuery(queryString);
    }

    public void flush() {
        getCurrentSession().flush();
    }

    public ClassMetadata getClassMetadata( Class entityClass ) {
        return getCurrentSession().getSessionFactory().getClassMetadata(entityClass);
    }

}
