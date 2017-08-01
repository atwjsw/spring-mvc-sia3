package com.habuma.spitter.persistence;

import com.habuma.spitter.domain.Spitter;
import com.habuma.spitter.domain.Spittle;
import com.habuma.spitter.persistence.daoSupport.SimpleJdbcSupportSpitterDao;
import com.habuma.spitter.persistence.jdbc.SpitterDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by wenda on 7/29/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:datasource-context-jdbcDaoSupport.xml")
public class PersistenceDaoSupportTest {

    @Autowired
    private SpitterDao spitterDao;

    @Test
    public void spitterDaoNotNull() {
        System.out.println(spitterDao);
        assertNotNull(spitterDao);
        assert(spitterDao instanceof SimpleJdbcSupportSpitterDao);
    }

    @Test
    public void testGetSpitterById() {
        Spitter spitter = spitterDao.getSpitterById(1L);
        System.out.println(spitter);
        assertEquals("habuma", spitter.getUsername());
        assertEquals(Long.valueOf(1), spitter.getId());
    }

    @Test
    public void testGetRecentSpittle() {
        List<Spittle> spittes = spitterDao.getRecentSpittle();
        System.out.println(spittes);

    }
//
//    @Test
//    public void testQueryForIdentity() {
//        Long id = spitterDao.queryForIdentity();
//        System.out.println(id);
//
//    }

}
