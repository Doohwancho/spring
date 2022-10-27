package cho.community.service.comment;

import cho.community.dto.comment.CommentCreateRequest;
import cho.community.dto.comment.CommentDto;
import cho.community.dto.comment.CommentReadCondition;
import cho.community.entity.board.Board;
import cho.community.entity.comment.Comment;
import cho.community.entity.user.User;
import cho.community.exception.BoardNotFoundException;
import cho.community.exception.CommentNotFoundException;
import cho.community.exception.MemberNotEqualsException;
import cho.community.exception.MemberNotFoundException;
import cho.community.repository.board.BoardRepository;
import cho.community.repository.comment.CommentRepository;
import cho.community.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<CommentDto> findAll(CommentReadCondition condition) {
        List<Comment> commentList = commentRepository.findByBoardId(condition.getBoardId());
        List<CommentDto> commentDtoList = new ArrayList<>();
        commentList.stream().forEach(i -> commentDtoList.add(new CommentDto().toDto(i)));
        return commentDtoList;
    }

    @Transactional
    public CommentDto create(CommentCreateRequest req, User user) {
        Board board = boardRepository.findById(req.getBoardId()).orElseThrow(BoardNotFoundException::new);
        Comment comment = new Comment(req.getContent(), user, board);
        commentRepository.save(comment);
        return new CommentDto().toDto(comment);
    }

    @Transactional
    public void delete(int id, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
        Board board = boardRepository.findById(comment.getBoard().getId()).orElseThrow(BoardNotFoundException::new);
        if (comment.getUser().equals(user)) {
            // 삭제 진행
            commentRepository.delete(comment);
        } else {
            throw new MemberNotEqualsException();
        }
    }

}
