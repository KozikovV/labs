package com.nixsolutions.web.action;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@ResultPath("/")
@Actions(value = { 
        @Action(value = "login", results = { 
                @Result(name = "success", location = "/login.jsp") })
})
public class RootAction extends ActionSupport {

    private static final Log LOG = LogFactory.getLog(RootAction.class);

    @Autowired
    private Producer captchaProducer;

    /**
     * Get captcha image.
     * 
     * @return null
     * @throws IOException
     */
    @Action(value="captchaImage")
    public String captchaImage() throws IOException {

        LOG.debug("captchaProducer: " + captchaProducer);

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();

        response.setDateHeader("Expires", 0);

        response.setHeader("Cache-Control",
                "no-store, no-cache, must-revalidate");

        response.addHeader("Cache-Control", "post-check=0, pre-check=0");

        response.setHeader("Pragma", "no-cache");

        response.setContentType("image/jpeg");

        String capText = captchaProducer.createText();

        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY,
                capText);

        BufferedImage bi = captchaProducer.createImage(capText);

        ServletOutputStream out = response.getOutputStream();

        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }

        return null;
    }
    
    public void setCaptchaProducer(Producer captchaProducer) {
        this.captchaProducer = captchaProducer;
    }

}
