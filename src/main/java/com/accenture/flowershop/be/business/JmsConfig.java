
package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.fe.dto.DiscountRequest;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.MessageListenerContainer;

import javax.jms.*;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;


@Configuration
public class JmsConfig {
    @Autowired
    private XMLConverter xmlConverter;

    @Autowired
    UserBusinessService ubs;

    @Value("${exportPath}")
    String properyPath;
    @Bean
    @Autowired
    public MessageListenerContainer containerFactory() {
        try {
            String url = ActiveMQConnectionFactory.DEFAULT_BROKER_URL;
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue inQueue = session.createQueue("IN_QUEUE");
            //Queue outQueue = session.createQueue("OUT_QUEUE");
            connection.start();
            //MessageProducer messageProducer = session.createProducer(outQueue);
            MessageConsumer messageConsumer = session.createConsumer(inQueue);
            messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        if (!(message instanceof TextMessage)) {
                            return;
                        }
                        TextMessage textMessage = (TextMessage) message;
                        String body = textMessage.getText();
                        DiscountRequest dr = (DiscountRequest)xmlConverter.doUnMarshalling(body);
                        ubs.setDiscount(dr.getCustomerId(), dr.getNewDiscount());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
       return null;
    }




    public void sendInOutQueue(String fileName) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(properyPath + fileName);
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
            Connection connection = connectionFactory.createConnection();
            connection.setClientID("OUT_QUEUE");
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = ((Session) session).createQueue("OUT_QUEUE");
            MessageProducer messageProducer = session.createProducer(queue);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(properyPath + "/" + fileName));
            connection.start();
            String line;
            String text = "";
            while ((line = bufferedReader.readLine()) != null) {
                text += line;
            }
            Message msg = session.createTextMessage(text);
            messageProducer.send(msg);
            connection.close();
            bufferedReader.close();
            fos.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
