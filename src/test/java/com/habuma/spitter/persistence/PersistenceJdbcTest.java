package com.habuma.spitter.persistence;

import com.habuma.spitter.domain.Spitter;
import com.habuma.spitter.persistence.jdbc.JdbcSpitterDao;
import com.habuma.spitter.persistence.jdbc.SpitterDao;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:datasource-context-jdbc.xml")
public class PersistenceJdbcTest {

//    @Autowired
//    private DataSource dataSource;
//    private JdbcSpitterDao jdbcSpitterDao;
    @Autowired
    private SpitterDao spitterDao;

    @Test
    public void spitterDaoNotNull() {
        System.out.println(spitterDao);
        assertNotNull(spitterDao);
        assert(spitterDao instanceof JdbcSpitterDao);
    }

    @Test
    public void testGetSpitterById() {
        System.out.println(spitterDao);
        Spitter spitter = spitterDao.getSpitterById(1);
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
//        assertEquals(2, results.size());
//        assertEquals("2:B", results.get(1));
    }
}

