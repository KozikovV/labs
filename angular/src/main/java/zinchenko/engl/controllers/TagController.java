package zinchenko.engl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zinchenko.engl.bean.Tag;
import zinchenko.engl.dao.TagDao;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagDao tagDao;

    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Tag> findAll(){
        return tagDao.findAll();
    }


    public TagDao getTagDao() {
        return tagDao;
    }

    public void setTagDao(TagDao tagDao) {
        this.tagDao = tagDao;
    }
}
