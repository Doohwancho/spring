package cho.community.controller.board;

import cho.community.dto.board.BoardCreateRequest;
import cho.community.dto.board.BoardUpdateRequest;
import cho.community.entity.board.Board;
import cho.community.response.Response;
import cho.community.service.board.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Board Controller", tags = "Board")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api")
public class BoardController {
    private final BoardService boardService;

    @ApiOperation(value = "게시글 생성", notes = "게시글을 작성합니다.")
    @PostMapping("/boards")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @ModelAttribute BoardCreateRequest req) {
        //@ModelAttribute 넣은 이유: Validation 제약 조건이 위배된다면, BindException이 발생하는데 BindExceptoin이 MethodArgumentNotValidException의 상위 클래스라서 둘다 잡을 수 있습니다.
        //그리고 게시글 생성에서는 form-data로 데이터를 받기 때문에 (이미지로 인해)
        //기존에 JSON으로 데이터를 받을 수 있는 방식인 @RequestBody를 사용하지 않습니다.

        return Response.success(boardService.create(req));
    }

    @ApiOperation(value = "게시글 목록 조회", notes = "게시글 목록을 조회합니다.")
    @GetMapping("/boards")
    @ResponseStatus(HttpStatus.OK)
    public Response findAllBoards(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        // ex) http://localhost:8080/api/boards/?page=0
        return Response.success(boardService.findAllBoards(pageable));
    }

    @ApiOperation(value = "게시글 단건 조회", notes = "게시글을 단건 조회합니다.")
    @GetMapping("/boards/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response findBoard(@ApiParam(value = "게시글 id", required = true) @PathVariable int id) {
        return Response.success(boardService.findBoard(id));
    }

    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정합니다.")
    @PutMapping("/boards/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response editBoard(@PathVariable int id, @Valid @ModelAttribute BoardUpdateRequest req) {
        return Response.success(boardService.editBoard(id, req));
    }

    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제합니다.")
    @DeleteMapping("/boards/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteBoard(@PathVariable int id) {
        boardService.deleteBoard(id);
        return Response.success();
    }

    @ApiOperation(value = "게시글 검색", notes = "게시글을 검색합니다.")
    @GetMapping("/boards/search")
    @ResponseStatus(HttpStatus.OK)
    public Response search(String keyword, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        // ex) http://localhost:8080/api/boards/search?page=0
        return Response.success(boardService.search(keyword, pageable));
    }

}


