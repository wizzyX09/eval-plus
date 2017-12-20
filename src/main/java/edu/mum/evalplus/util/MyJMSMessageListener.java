package edu.mum.evalplus.util;

import javax.jms.Message;
import javax.jms.MessageListener;

public class MyJMSMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
       /* ObjectMessage objMessage = (ObjectMessage) message;
        try {
            if (objMessage instanceof ClassOffered) {
                System.out.println("Yes===========================");
            }
           ClassOffered classOffered = (ClassOffered) objMessage.getObject();
            System.out.println("Message is received:" + classOffered.getName());
        } catch (JMSException e) {
            e.printStackTrace();
        }*/
    }
}
