package com.habuma.spitter.persistence;

import com.habuma.spitter.domain.Spitter;
import com.habuma.spitter.persistence.jdbc.SpitterDao;
import com.habuma.spitter.persistence.jdbcTemplate.SimpleJdbcTemplateSpitterDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by wenda on 7/29/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:datasource-context-jdbcTemplate.xml")
public class PersistenceJdbcTemplateTest {

    @Autowired
    private SpitterDao spitterDao;

    @Test
    public void spitterDaoNotNull() {
        System.out.println(spitterDao);
        assertNotNull(spitterDao);
        assert(spitterDao instanceof SimpleJdbcTemplateSpitterDao);
    }

    @Test
    public void testGetSpitterById() {
//        System.out.println(spitterDao);
        Spitter spitter = spitterDao.getSpitterById(1L);
        System.out.println(spitter);
        assertEquals("habuma", spitter.getUsername());
        assertEquals(Long.valueOf(1), spitter.getId());
    }

    @Autowired
    private DataSource dataSource;

    @Test
    public void testListSpitter() {
        assertNotNull(dataSource);
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);
        List<String> results = jdbc.query("select id, username, fullname from spitter", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getLong("id") + ":" + rs.getString("username") + ":" + rs.getString("fullname");
            }
        });

        for (String s: results) {
            System.out.println(s);
        }
    }



}
