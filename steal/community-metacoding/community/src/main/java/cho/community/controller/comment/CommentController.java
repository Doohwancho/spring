package cho.community.controller.comment;

import cho.community.dto.comment.CommentCreateRequest;
import cho.community.dto.comment.CommentReadCondition;
import cho.community.entity.user.User;
import cho.community.exception.MemberNotFoundException;
import cho.community.repository.user.UserRepository;
import cho.community.response.Response;
import cho.community.service.comment.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Comment Controller", tags = "Comment ")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;
    private final UserRepository userRepository;

    @ApiOperation(value = "댓글 목록 조회", notes = "댓글을 조회 합니다.")
    @GetMapping("/comments")
    @ResponseStatus(HttpStatus.OK)
    public Response findAll(@Valid CommentReadCondition condition) {
        return Response.success(commentService.findAll(condition));
    }

    @ApiOperation(value = "댓글 작성", notes = "댓글을 작성 합니다.")
    @PostMapping("/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody CommentCreateRequest req) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(MemberNotFoundException::new);

        return Response.success(commentService.create(req, user));
    }

    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제 합니다.")
    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response delete(@ApiParam(value = "댓글 id", required = true) @PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(MemberNotFoundException::new);

        commentService.delete(id, user);
        return Response.success();
    }
}