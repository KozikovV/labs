package zinchenko.engl.controllers;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.AbstractMultipartHttpServletRequest;
import zinchenko.engl.bean.Image;
import zinchenko.engl.dao.ImageDao;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageDao imageDao;

    @Value("#{main['main.fileStore']}")
    private String imageStorePath;

    @Autowired
    private ServletContext servletContext;

    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    List<Image> findAll() {
        return imageDao.findAll();
    }

//    @Transactional
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    Image save(HttpServletRequest request, MultipartFile file, Image image) throws IOException {
        String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
        File f = new File(servletContext.getRealPath("")+imageStorePath + File.separator + fileName);
        FileUtils.openOutputStream(f).write(file.getBytes());
//
        image.setFileName(fileName);
        imageDao.save(image);
        return image;
    }

    public ImageDao getImageDao() {
        return imageDao;
    }

    public void setImageDao(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    public String getImageStorePath() {
        return imageStorePath;
    }

    public void setImageStorePath(String imageStorePath) {
        this.imageStorePath = imageStorePath;
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
