package edu.mum.evalplus.util;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class SecurityAspect {

    @Before("execution(* edu.mum.evalplus.web.*.login(..))")
    public void checkUser() {
        System.out.println("User==========================================================");
    }

}
