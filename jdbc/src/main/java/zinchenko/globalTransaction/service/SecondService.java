package zinchenko.globalTransaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zinchenko.globalTransaction.dao.SecondDao;
import zinchenko.model.Bean;

@Service("secondService")
public class SecondService {

    @Autowired
    @Qualifier("secondDao")
    private SecondDao secondDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertBean(Bean bean){
        secondDao.insertBean(bean);
    }

    public SecondDao getSecondDao() {
        return secondDao;
    }

    public void setSecondDao(SecondDao secondDao) {
        this.secondDao = secondDao;
    }
}
