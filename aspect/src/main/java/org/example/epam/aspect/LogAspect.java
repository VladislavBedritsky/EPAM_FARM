package org.example.epam.aspect;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

@Component
public class LogAspect {

    public void beforeServiceMethodInvocation(JoinPoint joinPoint) {
        System.out.println("Invocation of method" + joinPoint.getSignature().getName());
    }
}
