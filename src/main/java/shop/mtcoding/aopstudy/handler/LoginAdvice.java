package shop.mtcoding.aopstudy.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import shop.mtcoding.aopstudy.handler.aop.LoginUser;
import shop.mtcoding.aopstudy.model.User;

@Aspect
@Component
public class LoginAdvice {

    @Around("execution(* shop.mtcoding.aopstudy.controller..*.*(..))")
    public Object loginUserAdvice(ProceedingJoinPoint jp) throws Throwable {
        Object result = jp.proceed();
        // Object[] param = new Object[10];
        Object[] args = jp.getArgs();
        System.out.println(result.toString());
        
        for(Object arg : args){
            if(arg instanceof LoginUser){
                HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                HttpSession session = req.getSession();
                
                User principal = (User) session.getAttribute("principal");
                args[0] = principal;
                System.out.println("테스트 : "+args[0]);
                result = jp.proceed(args);
            }
        }

        return result;
    }
}