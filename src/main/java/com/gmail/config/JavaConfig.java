package com.gmail.config;

import com.gmail.annotations.InjectRandomInt;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ReflectionUtils;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Created by Space on 09.05.2019.
 */
@Log4j
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.gmail.repository")
@PropertySource("classpath:config.properties")
@ComponentScan(basePackages = "com.gmail")
public class JavaConfig {
    @Autowired
    Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        driverManagerDataSource.setUrl(environment.getProperty("jdbc.url"));
        driverManagerDataSource.setUsername(environment.getProperty("jdbc.user"));
        driverManagerDataSource.setPassword(environment.getProperty("jdbc.password"));
        return driverManagerDataSource;
    }

    @Bean
    public LocalSessionFactoryBean factoryBean(DataSource dataSource, Properties properties) {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan(new String[]{"com.gmail.model"});
        factoryBean.setHibernateProperties(properties);
        return factoryBean;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);
        return jpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(new String[]{"com.gmail.model"});
        em.setJpaVendorAdapter(jpaVendorAdapter);
        return em;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public Properties properties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return properties;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("oleksii.chernii@gmail.com");
        return javaMailSender;
    }

    @Bean
    public BeanPostProcessor beanPostProcessor() {
        BeanPostProcessor beanPostProcessor = new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                Field[] fields = bean.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(InjectRandomInt.class) && (field.getType() == Integer.class || field.getType() == int.class)) {
                        field.setAccessible(true);
                        InjectRandomInt injectRandomInt = field.getAnnotation(InjectRandomInt.class);
                        ReflectionUtils.setField(field, bean, getRandomIntInRange(injectRandomInt.min(), injectRandomInt.max()));
                    }
                }
                return bean;
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                return bean;
            }
        };
        return beanPostProcessor;
    }

    private int getRandomIntInRange(int min, int max) {
        return min + (int) (Math.random() * (max - min));
    }
}
