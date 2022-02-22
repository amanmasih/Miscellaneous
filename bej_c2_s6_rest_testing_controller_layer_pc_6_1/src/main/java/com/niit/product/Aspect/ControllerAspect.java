package com.niit.product.Aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ControllerAspect {

    private static Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Pointcut("execution(* com.niit.product.Controller.ProductController.*(..) )")
    public void controllerMethods(){}//end of the function

    /*Advice:its an Action performed by Aspect
		   |:->Pointcut:the point to which the advice has to be Applied
           v
		=@=@=@==>  :->this is Program Execution
		  \|/
    JoinPoint:Its a point or a Location in the application where an advice can be plugged-in.*/

    //Lets create advices
    @Before("controllerMethods()")
    public void beforeAdvice(JoinPoint joinPoint) {
        logger.info("--------------@Before--------------");
        logger.debug("Method Name:::",joinPoint.getSignature().getName());
        logger.debug("Arguments:::", Arrays.toString(joinPoint.getArgs()));
        logger.info("*************************************************");
    }

    @After("controllerMethods()")
    public void afterAdvice(JoinPoint joinPoint){
        logger.info("----------@After-------------");
        logger.debug("Method Name:::",joinPoint.getSignature().getName());
        logger.debug("Arguments:::", Arrays.toString(joinPoint.getArgs()));
        logger.info("**************************************************");
    }

    @AfterReturning(value = "controllerMethods()",returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint,Object result){
        logger.info("--------------@AfterReturning--------------");
        logger.debug("Method Name::",joinPoint.getSignature().getName());
        logger.debug("Arguments:::", Arrays.toString(joinPoint.getArgs()));
        logger.debug("Result:::",result);
        logger.info("****************************************************");
    }

    @AfterThrowing(value = "controllerMethods()",throwing = "error")
    public void afterReturningAdvice(JoinPoint joinPoint,Throwable error)
    {
        logger.info("--------------@AfterThrowing--------------");
        logger.debug("Method Name:::",joinPoint.getSignature().getName());
        logger.debug("Arguments:::", Arrays.toString(joinPoint.getArgs()));
        logger.debug("Exception::::",error);
        logger.info("*********************************************************");
    }
}//end of class
