package zinchenko.globalTransaction.integrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zinchenko.globalTransaction.service.FirstService;
import zinchenko.globalTransaction.service.SecondService;
import zinchenko.model.Bean;

@Service("integrator")
public class IntergatorService {

    @Autowired
    @Qualifier("firstService")
    private FirstService firstService;

    @Autowired
    @Qualifier("secondService")
    private SecondService secondService;

    @Transactional
    public void insertBeans(Bean bean, Bean bean2){
        firstService.insertBean(bean);
        secondService.insertBean(bean2);
    }

    public FirstService getFirstService() {
        return firstService;
    }

    public void setFirstService(FirstService firstService) {
        this.firstService = firstService;
    }

    public SecondService getSecondService() {
        return secondService;
    }

    public void setSecondService(SecondService secondService) {
        this.secondService = secondService;
    }
}
