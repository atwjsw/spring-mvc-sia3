package com.habuma.spitter.persistence;

import com.habuma.spitter.domain.hibernate.Spitter;
import com.habuma.spitter.persistence.hibernate.SpitterDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by wenda on 7/31/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:datasource-context-hibernate.xml", "classpath:test-transaction-context-hibernate.xml"} )
public class PersistenceHibernateTest {

    @Autowired
    private SpitterDao spitterDao;

    @Test
    public void spitterDaoNotNull() {
        System.out.println(spitterDao);
        assertNotNull(spitterDao);
//        assert(spitterDao instanceof HibernateSpitterDao);
    }

    @Test
    public void testGetSpitterById() {
        Spitter spitter = spitterDao.getSpitterById(1L);
        System.out.println(spitter);
        assertEquals("habuma", spitter.getUsername());
        assertEquals(Long.valueOf(1), spitter.getId());
    }

    @Test
    public void testAddSpitter() {
        Spitter spitter = new Spitter();
        spitter.setUsername("daniel");
        spitter.setPassword("123456");
        spitter.setFullName("Daniel Wen");
        spitter.setEmail("daniel@dw.com");
        spitter.setUpdateByEmail(false);
        spitterDao.addSpitter(spitter);
//        testListSpitter();
        spitter = spitterDao.getSpitterById(3);
        System.out.println(spitter);
        assertEquals("daniel", spitter.getUsername());
        assertEquals(Long.valueOf(3), spitter.getId());
    }
}
