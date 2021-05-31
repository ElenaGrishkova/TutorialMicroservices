package ru.diasoft.tutorial.microservice.module2.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {
    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Pointcut("@annotation(Loggable)")
    public void annotateLoggableMethod() {
    }

    @Before("annotateLoggableMethod()")
    public void logBeforeCalling(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        logger.info("[Before] Calling method \'" + methodName + "\' with params: " + Arrays.toString(args));
    }

    @After("annotateLoggableMethod()")
    public void logAfterCalling(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        logger.info("[After] Ending method \'" + methodName + "\'");
    }

    //На будущее: execution(public * ru.diasoft.tutorial.microservice.module2.restservice.* - вот так была ошибка
    @AfterThrowing(pointcut = "execution(public * ru.diasoft.tutorial.microservice.module2.restservice..*(..)))", throwing = "throwable")
    public void logAfterReturning(JoinPoint jp, Throwable throwable) {
        String methodName = jp.getSignature().getName();
        logger.info("[AfterThrowing] Error calling method " + methodName + ", throwable = " + throwable);
    }
    @AfterReturning(pointcut = "execution(public * ru.diasoft.tutorial.microservice.module2.restservice.GreetingController.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint jp, Object result) {
        logger.info("[AfterReturning] Having greeting value: " + result.toString());
    }

    @Around("@annotation(Loggable)")
    public Object logExecutionTime(ProceedingJoinPoint jp) throws Throwable {
        long start = System.currentTimeMillis();
        logger.info("[Around start] " + jp.getSignature().getName() + " parameters = " + CollectionUtils.arrayToList(jp.getArgs()));
        Object proceed = jp.proceed();
        logger.info("[Around end] " + jp.getSignature().getName() + " result= " + proceed + ", time elapsed (ms) " + (System.currentTimeMillis() - start));
        return proceed;
    }

}