package org.example.jpashop.Nplus1;

import static java.lang.String.format;

import java.util.List;
import java.util.stream.IntStream;
import javax.persistence.EntityManager;
import org.example.jpashop.domain.forNplus1.Post;
import org.example.jpashop.repository.Nplus1.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/*
    TODO - N+1 problem simulated)
    ---
    상황
    Post:Comment(주인) = 1 : N (양방향), FetchType.LAZY
    Post는 10개, 각 post당 comment 3개씩 달려있는 상황임.
    
    여기서,
    Post.findAll() 했을 때, select * from Post; 하는 쿼리 1번만 발생함.
    
    ---
    문제는,
    가져온 post.getComment() 했을 때, post에 붙어있는 comments들 쿼리 해야하니까,
    post의 갯수 10번만큼 쿼리함.
    
    select
        comments0_.post_id as post_id3_2_0_,
        comments0_.id as id1_2_0_,
        comments0_.id as id1_2_1_,
        comments0_.content as content2_2_1_,
        comments0_.post_id as post_id3_2_1_
    from
        comment comments0_
    where
        comments0_.post_id=?
    
    이거 post 갯수만큼 10번함.
    
    
    ---
    Q. 애초에 가져올 때 join해서 한번에 가져올 순 없나?
    -> 그게 바로 fetch join (PostRepository.java에 findAll() 참조)
 */

@SpringBootTest
@Transactional
class PostRepositoryTest {
    
    @Autowired
    private EntityManager em;
    
    @Autowired
    private PostRepository postRepository;
    
    @Test
    @DisplayName("N + 1 발생 테스트")
    void test() {
        saveSampleData(); // 10개의 post와, 각각의 post마다 3개씩 댓글 저장
        em.flush();
        em.clear();
        System.out.println("------------ 영속성 컨텍스트 비우기 -----------\n\n");
        
        
        
        System.out.println("------------ POST 전체 조회 요청 ------------");
        List<Post> posts = postRepository.findAll();
        System.out.println("------------ POST 전체 조회 완료. [1번의 쿼리 발생]------------\n\n");
        
        
        
        
        System.out.println("------------ POST 제목 & 내용 조회 요청 ------------");
        System.out.println("size: " + posts.size());
        posts.forEach(it -> System.out.println("POST 제목: [%s], POST 내용: [%s]".formatted(it.getTitle(), it.getContent())));
        System.out.println("------------ POST 제목 & 내용 조회 완료. [추가적인 쿼리 발생하지 않음]------------\n\n");
        
        
        
        
        System.out.println("------------ POST에 달린 comment 내용 조회 요청 [조회된 POST의 개수(N=10) 만큼 추가적인 쿼리 발생]------------");
        posts.forEach(post -> {
            post.getComments().forEach(comment -> {
                System.out.println("POST 제목: [%s], COMMENT 내용: [%s]".formatted(comment.getPost().getTitle(), comment.getContent()));
            });
        });
        System.out.println("------------ POST에 달린 comment 내용 조회 완료 ------------\n\n");
    }
    
    private void saveSampleData() {
        final String postTitleFormat = "[%d] post-title";
        final String postContentFormat = "[%d] post-content";
        final String commentContentFormat = "[%d] comment-content";
        
        IntStream.rangeClosed(1, 5).forEach(i -> {
            Post post = new Post(format(postTitleFormat, i), format(postContentFormat, i));
            
            IntStream.rangeClosed(1, 3).forEach(j -> {
                post.writeComment(format(commentContentFormat, j));
            });
            
            postRepository.save(post);
        });
    }
}