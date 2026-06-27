package com.lab.proxies;

import com.lab.model.Comment;

public interface CommentNotificationProxy {
    void sendNotification(Comment comment);
}
