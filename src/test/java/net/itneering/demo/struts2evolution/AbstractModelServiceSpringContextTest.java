package net.itneering.demo.struts2evolution;

import org.springframework.test.AbstractSingleSpringContextTests;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * AbstractModelServiceSpringContextTest.
 *
 * @author Rene Gielen
 */
public abstract class AbstractModelServiceSpringContextTest extends AbstractSingleSpringContextTests {

    protected static final String[] CONTEXTFILES_TO_LOAD = {
            "classpath:/spring-context-s2evolution.xml",
            "classpath:/spring-test-context.xml"
    };

    PlatformTransactionManager transactionManager;
    TransactionStatus txStatus;

    public PlatformTransactionManager getTransactionManager() {
        if (transactionManager == null) {
        	this.transactionManager = (PlatformTransactionManager) getApplicationContext().getBean("transactionManager");
        }
        return transactionManager;
    }

    public void startTransaction() {
        PlatformTransactionManager txManager = getTransactionManager();
        if (txManager != null) {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            // explicitly setting the transaction name is something that can only be done programmatically
            def.setName("Test:"+getClass().getName());
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

            this.txStatus = txManager.getTransaction(def);
        } else {
            this.txStatus = null;
        }
    }

    public void commit() {
        if (txStatus != null) {
            PlatformTransactionManager txManager = getTransactionManager();
            if (txManager != null) {
                txManager.commit(txStatus);
                this.txStatus = null;
            }
        }
    }

    public void rollback() {
        if (txStatus != null) {
            PlatformTransactionManager txManager = getTransactionManager();
            if (txManager != null) {
                txManager.rollback(txStatus);
                this.txStatus = null;
            }
        }
    }

    /**
     * Default Config-Locations for our Testcases.
     *
     * @return The contextfile resources to load for Spring ApplicationContext
     *
     * @see #CONTEXTFILES_TO_LOAD
     */
    @Override
    protected String[] getConfigLocations() {
        return CONTEXTFILES_TO_LOAD;
    }

}