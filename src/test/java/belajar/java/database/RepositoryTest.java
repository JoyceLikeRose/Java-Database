package belajar.java.database;

import belajar.java.database.entity.Comment;
import belajar.java.database.repository.CommentRepository;
import belajar.java.database.repository.CommentRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RepositoryTest {
    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        commentRepository = new CommentRepositoryImpl();
    }

    @Test
    void testInsert() {
        Comment comment = new Comment("wildan@test.com", "hi");
        commentRepository.insert(comment);
        Assertions.assertNotNull(comment.getId());
        System.out.println(comment.getId());
    }

    @Test
    void testFinById() {
        Comment comment = commentRepository.findById(4508);
        Assertions.assertNotNull(comment);
        System.out.println(comment.getId());
        System.out.println(comment.getEmail());
        System.out.println(comment.getComment());

        Comment comment1 = commentRepository.findById(1000000);
        Assertions.assertNull(comment1);
    }

    @Test
    void testFindAll() {
        List<Comment> comments = commentRepository.findAll();
        Assertions.assertNotNull(comments);
        System.out.println(comments.size());
    }

    @Test
    void testFindByEmail() {
        List<Comment> comments = commentRepository.findByEmail("wildan@test.com");
        Assertions.assertNotNull(comments);
        System.out.println(comments.size());
    }
}
