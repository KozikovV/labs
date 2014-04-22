package zinchenko.springJdbcTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import zinchenko.model.Bean;

import javax.sql.DataSource;

@Repository
public class SpringJdbcTemplateDao extends JdbcDaoSupport{

    @Autowired
    public SpringJdbcTemplateDao(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public void insertBean(Bean bean){
        getJdbcTemplate().update("INSERT INTO BEAN (ID, NAME) VALUES (?, ?)",
                new Object[] {
                        bean.getId(),
                        bean.getName()
                });
    }

}
