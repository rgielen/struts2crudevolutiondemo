package net.itneering.demo.struts2evolution.service;

import org.apache.log4j.Logger;
import net.itneering.demo.struts2evolution.service.GenericDao;
import net.itneering.demo.struts2evolution.AbstractModelServiceSpringContextTest;

/**
 * GenericDaoTest test for given class.
 *
 * @author <a href="mailto:gielen@it-neering.net">Rene Gielen</a>
 */

public class GenericDaoTest extends AbstractModelServiceSpringContextTest {

    private static final Logger LOG = Logger.getLogger(GenericDaoTest.class);

    GenericDao genericDao;

    protected void onSetUp() throws Exception {
        super.onSetUp();
        this.genericDao = (GenericDao) getApplicationContext().getBean("genericDao");
    }

    public void testSetAllowCreateNew() throws Exception {
        assertNotNull(genericDao);
        assertNotNull(genericDao.getCurrentSession());
    }
}