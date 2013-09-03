package com.nixsolutions.service;

import javax.jms.Queue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    @Autowired
    private JmsTemplate jmsTemplate;
    
    @Autowired
    private Queue queue;

    private static final Log LOG = LogFactory.getLog(Sender.class);

    public void send(String message) {

        LOG.debug("message : " + message);

        jmsTemplate.convertAndSend(queue, message);

    }

}
