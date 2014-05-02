package zinchenko.engl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zinchenko.engl.bean.Category;
import zinchenko.engl.bean.Test;
import zinchenko.engl.dao.CategoryDao;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Controller
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    private CategoryDao categoryDao;

    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<Category> getAll(){
        return categoryDao.findAll();
    }

    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Category get(@PathVariable("id") Long id){
        return categoryDao.find(id);
    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

}
