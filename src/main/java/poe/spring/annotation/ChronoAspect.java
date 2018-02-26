package poe.spring.annotation;

import java.util.logging.*;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ChronoAspect {

	@Pointcut("execution(public * *(..))")
	public void publicMethod() {
	}

	@Around("@annotation(poe.spring.annotation.Chrono)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;
		System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");

		return proceed;
	}

	@Around("@within(poe.spring.annotation.Chrono)")
	public Object logExecutionTimeClass(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;
		System.out.println(joinPoint.getSignature() + " executed class in " + executionTime + "ms");
		return proceed;
	}

	// Logger
	// logger=Logger.getLogger(".springboot.src.main.java.poe.spring.annotation.ChronoAspect.java");
	// protected static Logger logger=
	// Logger.getLogger("myPackage.mySubPackage.myClasse");
	protected void logg(String s) {
		Logger logger = Logger.getLogger(".springboot.src.main.java.poe.spring.annotation.ChronoAspect.java");
		/*
		 * http://cyberzoide.developpez.com/tutoriels/java/logging/
		 * */
//		Handler fh = newFileHandler("ChronoAspect.log");
//		logger.addHandler(fh);
	}
}