package poe.spring.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import poe.spring.Application;

@Aspect
@Component
public class ChronoAspect {

	private static Logger log = LoggerFactory.getLogger(ChronoAspect.class);
	
	@Pointcut("execution(public * *(..))")
	public void publicMethod() {
	}

	@Around("@annotation(poe.spring.annotation.Chrono) || @within(poe.spring.annotation.Chrono)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;
		log.info("{0} executed in ${1}ms", joinPoint.getSignature(), executionTime);
		return proceed;
	}
}