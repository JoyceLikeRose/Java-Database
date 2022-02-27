package belajar.java.database.repository;

import belajar.java.database.entity.Comment;

import java.util.List;

public interface CommentRepository {
    void insert(Comment comment);
    Comment findById(Integer id);
    List<Comment> findAll();
    List<Comment> findByEmail(String email);
}
