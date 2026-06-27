package com.lab.repositories;
import com.lab.model.Comment;
import org.springframework.stereotype.Repository;
@Repository
public class FileCommentRepository implements CommentRepository {
    @Override
    public void storeComment(Comment comment) {
        System.out.println("  [File] Storing: " + comment);
    }
}