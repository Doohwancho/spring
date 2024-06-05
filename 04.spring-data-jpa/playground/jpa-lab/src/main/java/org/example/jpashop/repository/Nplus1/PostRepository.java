package org.example.jpashop.repository.Nplus1;

import java.util.List;
import org.example.jpashop.domain.forNplus1.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
    //TODO - N+1 solution) solution to N+1 Problem: fetch join
//    @Override
//    @Query("select p from Post p join fetch p.comments")
    List<Post> findAll();
}
