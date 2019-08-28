package com.accenture.flowershop.be.business;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Random;
@Component
@Aspect
public class DaoPerformanceAspect {
    Logger LOG = LoggerFactory.getLogger(DaoPerformanceAspect.class);
    @Around("execution(public * com.accenture.flowershop.be.repository..*(..))")
    public Object measureAccessSpeed(final ProceedingJoinPoint jp) throws Throwable {
        Date executionStart = new Date();
        try {
            return jp.proceed();
        }
        finally {
            LOG.info("Вызов DAO метода = {}.{} - {}мс", jp.getTarget().getClass().getName(),
                    jp.getSignature().getName(), new Date().getTime() - executionStart.getTime());
        }
    }
    @Before("execution(public * com.accenture.flowershop.be.repository..*(..))")
    public void sleep(final JoinPoint jp) {
        try {
            Thread.sleep(new Random().nextInt(100));
        } catch (InterruptedException e) { }
    }
}