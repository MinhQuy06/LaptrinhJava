package com.lab.repositories;
import com.lab.model.Comment;
public interface CommentRepository {
    void storeComment(Comment comment);
}