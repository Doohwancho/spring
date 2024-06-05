package org.example.jpashop.domain.forNplus1;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;

@Getter
@Entity
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
    
    public Post() {}
    
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
    
    public Comment writeComment(final String content) {
        Comment comment = new Comment(content, this);
        this.comments.add(comment);
        return comment;
    }
    
    public List<Comment> getComments() {
        return this.comments;
    }
}
