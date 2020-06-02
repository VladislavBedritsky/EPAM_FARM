package org.example.epam.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 * Aspect with logging logic
 *
 * @version 1.01 02 Jun 2020
 * @author Uladzislau Biadrytski
 */
@Component
public class LogAspect {

    private static Logger LOGGER = LogManager.getLogger(LogAspect.class);

    /**
     * Advice for logging method signature before invocation in pointcut.
     *
     * @param joinPoint Observation point where functionality is planned to be introduced.
     */
    public void beforeServiceMethodInvocation(JoinPoint joinPoint) {
        LOGGER.info("Invocation of method " + joinPoint.getSignature());
    }

    /**
     * Advice for logging time around method execution.
     *
     * @param joinPoint Observation of proceeding point where functionality is planned to be introduced.
     * @return Result of method execution
     */
    public Object aroundServiceMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long methodStartTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long methodEndTime = System.currentTimeMillis();
        LOGGER.info("Execution of method " + joinPoint.getSignature() + " took "
                + (methodEndTime-methodStartTime) + "msec.");
        return result;
    }
}
