package com.lab;
import com.lab.config.AppConfig;
import com.lab.model.Comment;
import com.lab.services.CommentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Khoi tao Spring Context ===");
        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        var service = context.getBean(CommentService.class);
        service.publishComment(new Comment("Alice", "Spring DI hoat dong!"));
        // Kiem tra Singleton: hai lan getBean() -> cung instance?
        var service2 = context.getBean(CommentService.class);
        System.out.println("Cung instance? " + (service == service2));
        context.close();
    }
}