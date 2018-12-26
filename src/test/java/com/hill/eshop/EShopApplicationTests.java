package com.hill.eshop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EShopApplicationTests {

    @Autowired
    DataSourceProperties dataSourceProperties;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void contextLoads() {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        System.out.println(dataSource);
        System.out.println(DataSource.class.getName());
        System.out.println(dataSourceProperties);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List l = jdbcTemplate.queryForList("select * from sys_user ");
        System.out.println(l);
    }

}

