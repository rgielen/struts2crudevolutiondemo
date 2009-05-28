package net.itneering.demo.struts2evolution.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * TransactionalSessionInterceptor allows the user to apply an Open-Transaction-In-View pattern
 * to Struts2 actions.
 *
 * @author Rene Gielen
 */
public class TransactionalSessionInterceptor extends MethodFilterInterceptor {

    private static final Logger LOG = Logger.getLogger(TransactionalSessionInterceptor.class);

    PlatformTransactionManager transactionManager;

    public void setTransactionManager( PlatformTransactionManager transactionManager ) {
        this.transactionManager = transactionManager;
    }

    protected String doIntercept( ActionInvocation actionInvocation ) throws Exception {
        if (transactionManager != null) {

            TransactionStatus status;
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setName("TransactionalSessionInterceptor@" + def.hashCode());
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

            // Create Transaction Context
            status = transactionManager.getTransaction(def);
            if (LOG.isDebugEnabled()) {
                LOG.debug("[doIntercept]: Transaction context created.");
            }

            try {
                // Invoke Action
                String result = actionInvocation.invoke();

                if (Action.INPUT.equals(result)) {
                    // Special treatment of INPUT result, forcing rollback
                    status.setRollbackOnly();

                    if (LOG.isInfoEnabled()) {
                        LOG.info("[doIntercept]: Setting current transaction to rollbackOnly due to INPUT action invocation result");
                    }
                }

                if (!status.isCompleted()) {
                    // Life's good, lets's commit
                    transactionManager.commit(status);

                    if (LOG.isDebugEnabled()) {
                        LOG.debug("[doIntercept]: Transaction ended regularly.");
                    }
                } else {
                    // for some reason, the transaction was already completed
                    if (LOG.isEnabledFor(Level.WARN)) {
                        LOG.warn("[doIntercept]: Transaction already marked as completed, not performing commit");
                    }
                }
                return result;

            } catch ( Exception e ) {

                // Bad things happened - an Exception popping up here is should force a rollback
                if (!status.isCompleted()) {

                    transactionManager.rollback(status);

                    if (LOG.isEnabledFor(Level.WARN)) {
                        LOG.warn("[doIntercept]: Rolled back due to uncatched exception: " + e.getMessage());
                    }
                } else {
                    // for some reason, the transaction was already completed
                    if (LOG.isEnabledFor(Level.WARN)) {
                        LOG.warn("[doIntercept]: Transaction already marked as completed, not performing rollback indicated by uncatched Excption " + e.getMessage());
                    }
                }
                // Finally, we'll be nicely rethrowing the transaction
                throw e;
            }
        } else {

            // Transaction manager was not configured, proceed without transaction context
            if (LOG.isInfoEnabled()) {
            	LOG.info("[doIntercept]: No TransactionManager found, proceeding without setting up transactional context.");
            }
            return actionInvocation.invoke();
        }
    }

}
