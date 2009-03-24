package net.itneering.demo.struts2evolution.service;

import net.itneering.demo.struts2evolution.model.IdHavingEntity;
import org.hibernate.Session;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * AbstractEntityService.
 *
 * @author Rene Gielen
 */
public abstract class AbstractEntityService {

    GenericDao genericDao;
    PlatformTransactionManager transactionManager;

    public GenericDao getGenericDao() {
        return genericDao;
    }

    public void setGenericDao( GenericDao genericDao ) {
        this.genericDao = genericDao;
    }

    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager( PlatformTransactionManager transactionManager ) {
        this.transactionManager = transactionManager;
    }

    public void init() {
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        TransactionStatus tx = getTransactionManager().getTransaction(txDef);
        initializeDefaultData();
        transactionManager.commit(tx);
    }

    public void initializeDefaultData() {
    }

    public Session getCurrentSession() {
        return getGenericDao().getCurrentSession();
    }

    @Transactional
    public Long saveOrUpdate( IdHavingEntity modelObject ) {
        if (modelObject != null) {
            getGenericDao().saveOrUpdate(modelObject);
            return modelObject.getId();
        } else {
            return null;
        }
    }

    @Transactional
    public Long update( IdHavingEntity modelObject ) {
        if (modelObject != null) {
            getGenericDao().update(modelObject);
            return modelObject.getId();
        } else {
            return null;
        }
    }

    @Transactional
    public Long save( IdHavingEntity modelObject ) {
        if (modelObject != null) {
            getGenericDao().save(modelObject);
            return modelObject.getId();
        } else {
            return null;
        }
    }

    @Transactional
    public void delete( IdHavingEntity modelObject ) {
        if (modelObject != null) {
            getGenericDao().delete(modelObject);
        }
    }

    /**
     * Die Entitaeten Klasse, fuer dieser Service verantwortlich zeichnet.
     *
     * @return Die Klasse.
     */
    public abstract Class entityClass();

    /**
     * Lade Entitaets-Objekt zu gegebener ID aus der Datenbank.
     *
     * @param id ID des Objekts, das geladen werden soll
     *
     * @return das gefundene Objekt, oder <tt>null</tt> falls nichts gefunden wurde.
     */
    @SuppressWarnings("unchecked")
    @Transactional
    public <T extends IdHavingEntity> T get( Long id ) {
       if (id != null) {
           return (T) getGenericDao().get(entityClass(), id);
       } else {
           return null;
       }
   }

}
