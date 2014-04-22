package zinchenko.springJdbcTemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zinchenko.model.Bean;

import javax.sql.DataSource;

public class SpringJdbcMain {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:zinchenko/springJdbcTemplate/springTemplateJdbcTransaction-appContext.xml");
        SpringJdbcTemplateService service = (SpringJdbcTemplateService) context.getBean("service");
        DataSource dataSource = (DataSource) context.getBean("dataSource");
//        Connection connection = dataSource.getConnection();
//        connection.createStatement().executeUpdate("drop table if exists bean;");
//        connection.createStatement().executeUpdate("create table bean (id int not null primary key, name varchar(250) not null);");
//        connection.createStatement().executeUpdate("insert into bean (id, name) values (1, 'name1');");
//        connection.commit();
//        connection.close();


        Bean bean1 = new Bean();
        bean1.setId(6);
        bean1.setName("name14");

        Bean bean2 = new Bean();
        bean2.setId(7);
        bean2.setName("name15");

//        service.insertBeansSpringJdbcTenlate(bean1, bean2);
        service.insertBeansOnlyJdbc(bean1, bean2);

    }



}
