
package com.accenture.flowershop.be.business;

import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.fe.dto.DiscountRequest;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.MessageListenerContainer;

import javax.annotation.PostConstruct;
import javax.jms.*;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;


@Configuration
public class JmsConfig {
    @Autowired
    private XMLConverter xmlConverter;

    @Autowired
    private UserBusinessService ubs;

    @Value("${exportPath}")
    private String properyPath;
    private Session session;
    private Queue inQueue;
    private Queue outQueue;
    private static final Logger LOG = LoggerFactory.getLogger(JmsConfig.class);
    @PostConstruct
    void init() {
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
            Connection connection = connectionFactory.createConnection();
            connection.setClientID("OUT_QUEUE");
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            inQueue = session.createQueue("IN_QUEUE");
            outQueue = session.createQueue("OUT_QUEUE");
            connection.start();
        }
       catch (JMSException e){

       }
    }

    @Bean
    @Autowired
    public MessageListenerContainer containerFactory() {
        try {
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
                        LOG.info("Ошибка при прослушивании очереди :" + this.getClass() + ";");
                    }
                }
            });
        } catch (Exception e) {
            LOG.info("Ошибка при прослушивании очереди :" + this.getClass() + ";");
        }
       return null;
    }




    public void sendInOutQueue(String fileName) {
        try (FileOutputStream fos = new FileOutputStream(properyPath + fileName)){
            MessageProducer messageProducer = session.createProducer(outQueue);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(properyPath + "/" + fileName));
            String line;
            String text = "";
            while ((line = bufferedReader.readLine()) != null) {
                text += line;
            }
            Message msg = session.createTextMessage(text);
            messageProducer.send(msg);
            bufferedReader.close();
        }
        catch (Exception e) {
            LOG.info("Ошибка при прослушивании очереди :" + this.getClass() + ";");
        }
    }
}
