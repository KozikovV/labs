package zinchenko.globalTransaction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zinchenko.globalTransaction.integrator.IntergatorService;
import zinchenko.model.Bean;


public class GlobalTransactionMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:zinchenko/globalTransaction/globalTransaction-appContext.xml");
        IntergatorService integrator = (IntergatorService) context.getBean("integrator");

        integrator.insertBeans(
                new Bean(6, "name1"),
                new Bean(4, "name1")
        );
    }

}
