package com.nixsolutions.service;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "listener")
public class Receiver implements MessageListener {
    
    public static List<String> list = new ArrayList<String>();

    private static final Log LOG = LogFactory.getLog(Receiver.class);

    @Autowired
    private ServletContext context;

    public void onMessage(Message message) {

        if (message instanceof TextMessage) {

            String textMessage = null;
            try {
                textMessage = ((TextMessage) message).getText();
            } catch (JMSException e) {
                LOG.error("exception ", e);
                throw new RuntimeException(e);
            }
            
            LOG.debug("text of message : "+textMessage);
            
            list.add(textMessage);

            LOG.debug("messages "+list);

        }

    }

}
