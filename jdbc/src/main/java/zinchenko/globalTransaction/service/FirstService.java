package zinchenko.globalTransaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zinchenko.globalTransaction.dao.FirstDao;
import zinchenko.model.Bean;

@Service("firstService")
public class FirstService {

    @Autowired
    @Qualifier("firstDao")
    private FirstDao firstDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertBean(Bean bean){
        firstDao.insertBean(bean);
    }

    public FirstDao getFirstDao() {
        return firstDao;
    }

    public void setFirstDao(FirstDao firstDao) {
        this.firstDao = firstDao;
    }
}
