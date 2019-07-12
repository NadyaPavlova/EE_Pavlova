
package com.accenture.flowershop.be;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(){
      ApplicationContext context = new AnnotationConfigApplicationContext("flowershop.src.main.resource.config.application-context.xml");

    }
}
