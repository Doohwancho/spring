package me.hajoo.jpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    Long id;

    String title;
    String content;

    @OneToMany(mappedBy = "board")
    List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment){
        comment.setBoard(this);
        comments.add(comment);
    }
}
