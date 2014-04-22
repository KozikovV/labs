package zinchenko.globalTransaction.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import zinchenko.model.Bean;

import javax.sql.DataSource;

@Repository("firstDao")
public class FirstDao extends JdbcDaoSupport{

    @Autowired
    public FirstDao(DataSource firstDataSource) {
        setDataSource(firstDataSource);
    }

    public void insertBean(Bean bean){
        getJdbcTemplate().update("INSERT INTO BEAN (ID, NAME) VALUES (?, ?)",
                new Object[]{
                        bean.getId(),
                        bean.getName()
                }
        );
    }

}
