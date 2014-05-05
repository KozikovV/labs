package zinchenko.engl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import zinchenko.engl.bean.Image;
import zinchenko.engl.dao.ImageDao;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageDao imageDao;

    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<Image> findAll(){
        return imageDao.findAll();
    }

    @Transactional
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    Image save(@RequestParam("file") MultipartFile file){

        return new Image();
    }

    public ImageDao getImageDao() {
        return imageDao;
    }

    public void setImageDao(ImageDao imageDao) {
        this.imageDao = imageDao;
    }
}
