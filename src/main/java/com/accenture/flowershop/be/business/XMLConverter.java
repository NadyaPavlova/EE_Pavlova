package com.accenture.flowershop.be.business;


import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.fe.dto.DiscountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;

@XmlRootElement
public class XMLConverter {
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    @Autowired
    UserBusinessService ubs;

    @Value("${exportPath}")
    String properyPath;

    public void convertFromObjectToXML(Object object, String fileName) throws IOException {
        try {
            FileOutputStream os = new FileOutputStream(properyPath + "/" + fileName);
            getMarshaller().marshal(object, new StreamResult(os));
        } catch (Exception e) {
            throw e;
        }
    }

    public Object convertFromXMLToObject(String xmlfile) throws IOException {
        try {
            FileInputStream is = new FileInputStream(xmlfile);
            return getUnmarshaller().unmarshal(new StreamSource(is));
        } catch (Exception e) {
            throw e;
        }
    }

    public Object doUnMarshalling(String file) {

        try {
            unmarshaller.supports(DiscountRequest.class);
            return unmarshaller.unmarshal(new StreamSource(new StringReader(file)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Marshaller getMarshaller() {
        return marshaller;
    }

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

}