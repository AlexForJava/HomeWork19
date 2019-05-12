package com.gmail.service;

import com.gmail.config.JavaConfig;
import com.gmail.model.User;
import junit.framework.TestCase;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

public class UserServiceImplTest extends TestCase {
    private static final String SQL_GET_COUNT = "SELECT count(*) FROM users";

    private UserService userService;
    private DataSource dataSource;


    @Before
    public void init() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        userService = applicationContext.getBean(UserServiceImpl.class);
        System.out.println(userService);
        dataSource = applicationContext.getBean("dataSource", DataSource.class);
        System.out.println(dataSource);
    }

    public void testSave() throws Exception {
    }

    public void testGetById() throws Exception {

    }

    public void testGetByName() throws Exception {
        List<User> list = userService.getByName("Alex");
        assertNotNull(list);
        list.forEach(user -> {
            assertNotNull(user.getBirthdayDate());
            assertNotNull(user.getName());
            assertNotNull(user.getRandom());
            assertNotNull(user.getEmail());
        });
    }

    public void testGetByBirthdayDate() throws Exception {
        List<User> list = userService.getByBirthdayDate(LocalDate.now());
        assertNotNull(list);
        list.forEach(user -> {
            assertNotNull(user.getBirthdayDate());
            assertNotNull(user.getName());
            assertNotNull(user.getRandom());
            assertNotNull(user.getEmail());
        });
    }

    public void testDeleteUser() throws Exception {

        //userService.deleteUser(user);

    }

    public void testDeleteById() throws Exception {
        /*JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int before = jdbcTemplate.queryForObject(SQL_GET_COUNT, Integer.class);
        userService.deleteById(1L);
        int after = jdbcTemplate.queryForObject(SQL_GET_COUNT, Integer.class);
        assertFalse(before == after);*/
    }
}
