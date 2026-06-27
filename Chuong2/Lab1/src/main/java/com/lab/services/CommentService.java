package com.lab.services;
import com.lab.model.Comment;
import com.lab.proxies.CommentNotificationProxy;
import com.lab.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
@Service
public class CommentService {
    // Constructor Injection: field final -> immutable -> thread-safe
    private final CommentRepository        commentRepository;
    private final CommentNotificationProxy notificationProxy;
    public CommentService(
            @Qualifier("DBCommentRepository") CommentRepository repo,
            CommentNotificationProxy proxy) {
        this.commentRepository = repo;
        this.notificationProxy  = proxy;
        System.out.println("[INIT] CommentService created!");
    }

    public void publishComment(Comment comment) {
        System.out.println("\n>> Publishing: " + comment);
        commentRepository.storeComment(comment);
        notificationProxy.sendNotification(comment);
        System.out.println(">> Done!\n");
    }
}