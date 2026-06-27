package com.lab.lifecycle;

import com.lab.lifecycle.config.AppConfig;
import com.lab.lifecycle.managed.ManagedBean;
import com.lab.lifecycle.prototype.RequestProcessor;
import com.lab.lifecycle.singleton.ServiceA;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
//        System.out.println("--- Before context ---");
//        var ctx = new AnnotationConfigApplicationContext(AppConfig.class);
//        System.out.println("--- After context  ---\n");
//        ctx.getBean(ServiceA.class).doWork();
//        ctx.close();


        System.out.println("\n=== Vong doi Bean ===");
        var ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        var bean = ctx.getBean(ManagedBean.class);
        bean.doWork();
        System.out.println("\n--- Dong context (ctx.close()) ---");


        System.out.println("\n=== Singleton ===");

        var a1 = ctx.getBean(ServiceA.class);
        var a2 = ctx.getBean(ServiceA.class);
        System.out.println("a1 == a2 ? " + (a1 == a2));        // -> true
        System.out.println("\n=== Prototype ===");
        var p1 = ctx.getBean(RequestProcessor.class);
        var p2 = ctx.getBean(RequestProcessor.class);
        var p3 = ctx.getBean(RequestProcessor.class);
        p1.process("Request A");
        p2.process("Request B");
        p3.process("Request C");
        System.out.println("p1 == p2 ? " + (p1 == p2));        // -> false
        System.out.println("p1 == p3 ? " + (p1 == p3));        // -> false
        ctx.close();
    }
}