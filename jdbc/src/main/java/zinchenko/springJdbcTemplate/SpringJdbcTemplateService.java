package zinchenko.springJdbcTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zinchenko.model.Bean;

@Service("service")
public class SpringJdbcTemplateService {

    @Autowired
    private SpringJdbcTemplateDao springJdbcTemplateDao;

    @Autowired
    private JdbcDao jdbcDao;

    @Transactional
    public void insertBeansSpringJdbcTenlate(Bean bean, Bean bean2){
        springJdbcTemplateDao.insertBean(bean);
        springJdbcTemplateDao.insertBean(bean2);
    }

    @Transactional
    public void insertBeansOnlyJdbc(Bean bean, Bean bean2){
        jdbcDao.insertBean(bean);
        jdbcDao.insertBean(bean2);
    }

    public SpringJdbcTemplateDao getSpringJdbcTemplateDao() {
        return springJdbcTemplateDao;
    }

    public void setSpringJdbcTemplateDao(SpringJdbcTemplateDao springJdbcTemplateDao) {
        this.springJdbcTemplateDao = springJdbcTemplateDao;
    }

    public JdbcDao getJdbcDao() {
        return jdbcDao;
    }

    public void setJdbcDao(JdbcDao jdbcDao) {
        this.jdbcDao = jdbcDao;
    }
}
