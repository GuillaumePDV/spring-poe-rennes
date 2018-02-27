package poe.spring.annotation;

//import java.util.logging.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import ch.qos.logback.classic.Logger;

@Aspect
@Component
public class ChronoAspect {
	
	@Autowired
	Logger logger;
	

	@Pointcut("execution(public * *(..))")
	public void publicMethod() {
	}

	@Around("@annotation(poe.spring.annotation.Chrono)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;
		logger.debug(String.format("%s executed class in %s ms", joinPoint.getSignature(), executionTime));
		return proceed;
	}

	@Around("@within(poe.spring.annotation.Chrono)")
	public Object logExecutionTimeClass(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;
		logger.debug(String.format("%s executed class in %s ms", joinPoint.getSignature(), executionTime));
		return proceed;
	}
}