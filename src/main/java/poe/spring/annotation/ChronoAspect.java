package poe.spring.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ChronoAspect {

	@Autowired
	Logger logger;

//	@Pointcut("execution(public * *(..))")
//	public void publicMethod() {
//	}

	@Around("@annotation(poe.spring.annotation.Chrono)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;
		String logg = String.format("%s executed class in %s ms", joinPoint.getSignature(), executionTime);
		logger.debug(logg);
		return proceed;
	}

	@Around("@within(poe.spring.annotation.Chrono)")
	public Object logExecutionTimeClass(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;
		String logg = String.format("%s executed class in %s ms", joinPoint.getSignature(), executionTime);
		logger.debug(logg);
		return proceed;
	}
}