package com.nixsolutions.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;

import com.nixsolutions.service.Receiver;

@Controller
public class ReceiveController {
    
    private static final Log LOG = LogFactory.getLog(ReceiveController.class);

    @Autowired
    private Receiver receiver;
    
    @Autowired
    private ServletContext context;
    
    @RequestMapping("/receive.htm")
    public String receive(ModelMap map){
        
        LOG.debug("receive()");
        
        List<String> list = Receiver.list;
        
        if (!list.isEmpty()) {
            map.put("messages", list);
            LOG.debug("messages "+list);
        }else {
            LOG.debug("messages is empty, list = "+list);
        }
        
        return "receive";
    }
    
    
    
}
