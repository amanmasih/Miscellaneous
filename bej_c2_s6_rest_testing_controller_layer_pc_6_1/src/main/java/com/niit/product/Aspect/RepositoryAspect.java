package com.niit.product.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Aspect
@Component
public class RepositoryAspect {

    private static Logger logger = LoggerFactory.getLogger(RepositoryAspect.class);

    @Pointcut("execution(* com.niit.product.Repository.ProductRepository.*(..) )")
    public void repositoryMethods(){}//end of the function

    /*Advice:its an Action performed by Aspect
		   |:->Pointcut:the point to which the advice has to be Applied
           v
		=@=@=@==>  :->this is Program Execution
		  \|/
    JoinPoint:Its a point or a Location in the application where an advice can be plugged-in.*/

    //Lets create advices
    @Before("repositoryMethods()")
    public void beforeAdvice(JoinPoint joinPoint) {
        logger.info("--------------@Before--------------");
        logger.debug("Method Name:::",joinPoint.getSignature().getName());
        logger.debug("Arguments:::", Arrays.toString(joinPoint.getArgs()));
        logger.info("*************************************************");
    }

    @After("repositoryMethods()")
    public void afterAdvice(JoinPoint joinPoint){
        logger.info("----------@After-------------");
        logger.debug("Method Name:::",joinPoint.getSignature().getName());
        logger.debug("Arguments:::", Arrays.toString(joinPoint.getArgs()));
        logger.info("**************************************************");
    }

    @AfterReturning(value = "repositoryMethods()",returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint,Object result){
        logger.info("--------------@AfterReturning--------------");
        logger.debug("Method Name::",joinPoint.getSignature().getName());
        logger.debug("Arguments:::", Arrays.toString(joinPoint.getArgs()));
        logger.debug("Result:::",result);
        logger.info("****************************************************");
    }

    @AfterThrowing(value = "repositoryMethods()",throwing = "error")
    public void afterReturningAdvice(JoinPoint joinPoint,Throwable error)
    {
        logger.info("--------------@AfterThrowing--------------");
        logger.debug("Method Name:::",joinPoint.getSignature().getName());
        logger.debug("Arguments:::", Arrays.toString(joinPoint.getArgs()));
        logger.debug("Exception::::",error);
        logger.info("*********************************************************");
    }
}//end of the class
