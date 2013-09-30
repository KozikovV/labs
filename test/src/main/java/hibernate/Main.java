package hibernate;

import hibernate.dao.CategoryDao;
import hibernate.dao.ProductDao;
import hibernate.domain.Category;
import hibernate.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User: zinchenko
 * Date: 9/30/13
 */
@Component
public class Main {

    @Autowired
    private ProductDao productDao;

    public static void main(String[] args) {
        new Main().execute();
    }

    private void execute(){
        ApplicationContext applicationContext = getAplicationContext();
        ProductDao productDao = (ProductDao) applicationContext.getBean("productDao");
        CategoryDao categoryDao = (CategoryDao) applicationContext.getBean("categoryDao");

        List<Product> products = productDao.findAll();
        List<Category> categories = categoryDao.findAll();
    }

    private ApplicationContext getAplicationContext(){
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        return applicationContext;
    }


    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}
