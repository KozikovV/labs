package com.nixsolutions.controller;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nixsolutions.service.Sender;

@Controller
public class SendController {

    private static final Log LOG = LogFactory.getLog(SendController.class);

    @Autowired
    private Sender sender;

    @RequestMapping("/sendForm")
    public String sendForm() {

        LOG.debug("invoked sendForm()");

        return "send";
    }

    @RequestMapping("/send")
    public String send(@RequestParam String message) {

        LOG.debug("message : "+message);

        sender.send(message);

        return "send";
    }

}
