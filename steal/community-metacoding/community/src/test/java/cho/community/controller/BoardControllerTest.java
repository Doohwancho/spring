package cho.community.controller;


import cho.community.controller.board.BoardController;
import cho.community.dto.board.BoardCreateRequest;
import cho.community.dto.board.BoardUpdateRequest;
import cho.community.entity.board.Board;
import cho.community.entity.user.User;
import cho.community.repository.board.BoardRepository;
import cho.community.repository.user.UserRepository;
import cho.community.service.board.BoardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static cho.community.factory.UserFactory.createUserWithAdminRole;

@ExtendWith(MockitoExtension.class)
public class BoardControllerTest {
    @InjectMocks
    BoardController boardController;

    @Mock
    BoardService boardService;
    BoardRepository boardRepository;
    UserRepository userRepository;
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
    }

//    @Test
//    @DisplayName("????????? ?????? - ?????? token ?????? ????????? failed")
//    public void createTest() throws Exception {
//        // given
//        ArgumentCaptor<BoardCreateRequest> boardCreateRequestArgumentCaptor = ArgumentCaptor.forClass(BoardCreateRequest.class);
//        List<MultipartFile> images = new ArrayList<>();
//        images.add(new MockMultipartFile("test1", "test1.PNG", MediaType.IMAGE_PNG_VALUE, "test1".getBytes()));
//        images.add(new MockMultipartFile("test2", "test2.PNG", MediaType.IMAGE_PNG_VALUE, "test2".getBytes()));
//        BoardCreateRequest req = new BoardCreateRequest("title", "content", images);
//
//        User user = createUserWithAdminRole();
//        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getId(), "", Collections.emptyList());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        given(userRepository.findByUsername(authentication.getName())).willReturn(Optional.of(user));
//
//
//        // when, then
//        mockMvc.perform(
//                        multipart("/api/boards")
//                                .file("images", images.get(0).getBytes())
//                                .file("images", images.get(1).getBytes())
//                                .param("title", req.getTitle())
//                                .param("content", req.getContent())
//                                .with(requestBoardProcessor -> {
//                                    requestBoardProcessor.setMethod("POST");
//                                    return requestBoardProcessor;
//                                })
//                                .contentType(MediaType.MULTIPART_FORM_DATA))
//                .andExpect(status().isCreated());
//
//        verify(boardService).create(boardCreateRequestArgumentCaptor.capture());
//        BoardCreateRequest capturedReq = boardCreateRequestArgumentCaptor.getValue();
//        assertThat(capturedReq.getImages().size()).isEqualTo(2);
//    }

//    @Test
//    @DisplayName("????????? ?????? - ?????? token ?????? ????????? failed")
//    public void searchTest() throws Exception {
//        // given
//        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "id");
//        List<Board> result = boardRepository.findByTitleContaining("keyword", pageable);
//
//        // when, then
//        assertThat(result).isEqualTo(null);
//    }


//    @Test
//    @DisplayName("?????? ????????? ?????? (?????????)")
//    public void findAllBoardsTest() throws Exception {
//        // given
//        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "id");
//        Page<Board> result = boardRepository.findAll(pageable);
//
//        // when, then
//        assertThat(result).isEqualTo(null);
//    }


    @Test
    @DisplayName("????????? ?????? ?????? ")
    public void findBoardTest() throws Exception {
        // given
        int id = 1;

        // when, then
        mockMvc.perform(
                        get("/api/boards/{id}", id))
                .andExpect(status().isOk());

        verify(boardService).findBoard(id);
    }


//    @Test
//    @DisplayName("????????? ??????")
//    public void bestBoardsTest() throws Exception {
//        // given
//        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "id");
//        Page<Board> result = boardRepository.findByLikedGreaterThanEqual(pageable, 3);
//
//        // when, then
//        assertThat(result).isEqualTo(null);
//    }

//    @Test
//    @DisplayName("????????? ????????? ??? ?????? - ?????? token ?????? ????????? failed")
//    public void likeBoardTest() throws Exception {
//        // given
//        int id = 1;
//
//        User user = createUserWithAdminRole();
//        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getId(), "", Collections.emptyList());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        given(userRepository.findByUsername(authentication.getName())).willReturn(Optional.of(user));
//
//
//        // when, then
//        mockMvc.perform(
//                        post("/api/boards/{id}", id)
//                                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
//        verify(boardService).likeBoard(id, user);
//    }


//    @Test
//    @DisplayName("???????????? ?????? ??? ?????? - ?????? token ?????? ????????? failed")
//    public void favoriteBoardTest() throws Exception {
//        // given
//        int id = 1;
//
//        User user = createUserWithAdminRole();
//        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getId(), "", Collections.emptyList());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        given(userRepository.findByUsername(authentication.getName())).willReturn(Optional.of(user));
//
//
//        // when, then
//        mockMvc.perform(
//                        post("/api/boards/{id}/favorites", id)
//                                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
//        verify(boardService).favoriteBoard(id, user);
//    }


//    @Test
//    @DisplayName("????????? ?????? - ?????? token ?????? ????????? failed")
//    public void editBoardTest() throws Exception {
//        // given
//        ArgumentCaptor<BoardUpdateRequest> boardUpdateRequestArgumentCaptor = ArgumentCaptor.forClass(BoardUpdateRequest.class);
//
//        List<MultipartFile> addedImages = new ArrayList<>();
//        addedImages.add(new MockMultipartFile("test1", "test1.PNG", MediaType.IMAGE_PNG_VALUE, "test1".getBytes()));
//        addedImages.add(new MockMultipartFile("test2", "test2.PNG", MediaType.IMAGE_PNG_VALUE, "test2".getBytes()));
//
//        List<Integer> deletedImages = List.of(1, 2);
//
//        BoardUpdateRequest req = new BoardUpdateRequest("title", "content", addedImages, deletedImages);
//
//        User user = createUserWithAdminRole();
//        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getId(), "", Collections.emptyList());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        given(userRepository.findByUsername(authentication.getName())).willReturn(Optional.of(user));
//
//
//        // when, then
//        mockMvc.perform(
//                        multipart("/api/boards/{id}", 1)
//                                .file("addedImages", addedImages.get(0).getBytes())
//                                .file("addedImages", addedImages.get(1).getBytes())
//                                .param("deletedImages", String.valueOf(deletedImages.get(0)), String.valueOf(deletedImages.get(1)))
//                                .param("title", req.getTitle())
//                                .param("content", req.getContent())
//                                .with(requestBoardProcessor -> {
//                                    requestBoardProcessor.setMethod("PUT");
//                                    return requestBoardProcessor;
//                                })
//                                .contentType(MediaType.MULTIPART_FORM_DATA))
//                .andExpect(status().isOk());

//        verify(boardService).editBoard(anyInt(), boardUpdateRequestArgumentCaptor.capture(), user);
//
//        BoardUpdateRequest capturedReq = boardUpdateRequestArgumentCaptor.getValue();
//        List<MultipartFile> capturedAddedImages = capturedReq.getAddedImages();
//        assertThat(capturedAddedImages.size()).isEqualTo(2);
//
//        List<Integer> capturedDeletedImages = capturedReq.getDeletedImages();
//        Assertions.assertThat(capturedDeletedImages.size()).isEqualTo(2);
//        Assertions.assertThat(capturedDeletedImages).contains(deletedImages.get(0), deletedImages.get(1));
//    }
}