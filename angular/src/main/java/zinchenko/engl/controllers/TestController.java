package zinchenko.engl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zinchenko.engl.bean.Category;
import zinchenko.engl.bean.Test;
import zinchenko.engl.dao.TestDao;

import javax.transaction.Transactional;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestDao testDao;

    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Test get(@PathVariable("id") Long id){
        return testDao.find(id);
    }

    public TestDao getTestDao() {
        return testDao;
    }

    public void setTestDao(TestDao testDao) {
        this.testDao = testDao;
    }
}
