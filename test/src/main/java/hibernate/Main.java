package hibernate;

import hibernate.dao.CategoryDao;
import hibernate.dao.DomainDao;
import hibernate.dao.ProductDao;
import hibernate.domain.Category;
import hibernate.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

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
        DomainDao domainDao = (DomainDao) applicationContext.getBean("domainDao");

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        domainDao.criteria();



    }

    private ApplicationContext getAplicationContext(){
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        return applicationContext;
    }

    private Category createCategory(){
        Category category = new Category();
        category.setName("asdxxas");
        return category;
    }

    private Product createProduct(){
        Product product = new Product();
        product.setTitle("ttsdf");
        return product;
    }


    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}
