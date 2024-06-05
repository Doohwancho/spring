package cho.community.service.admin;

import cho.community.dto.board.BoardSimpleDto;
import cho.community.dto.user.UserDto;
import cho.community.entity.board.Board;
import cho.community.entity.user.User;
import cho.community.exception.BoardNotFoundException;
import cho.community.exception.MemberNotEqualsException;
import cho.community.exception.NotReportedException;
import cho.community.repository.board.BoardRepository;
import cho.community.repository.report.BoardReportRepository;
import cho.community.repository.report.UserReportRepository;
import cho.community.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final UserReportRepository userReportRepository;
    private final BoardReportRepository boardReportRepository;

    @Transactional(readOnly = true)
    public List<UserDto> manageReportedUser() {
        List<User> users = userRepository.findByReportedIsTrue();
        List<UserDto> usersDto = new ArrayList<>();
        users.stream().forEach(i -> usersDto.add(new UserDto().toDto(i)));
        return usersDto;
    }

    @Transactional
    public UserDto unlockUser(int id) {
        User user = userRepository.findById(id).orElseThrow(MemberNotEqualsException::new);
        if(!user.isReported()) {
            throw new NotReportedException();
        }
        user.setReported(false);
        userReportRepository.deleteAllByReportedUserId(id);
        return UserDto.toDto(user);
    }


    @Transactional(readOnly = true)
    public List<BoardSimpleDto> manageReportedBoards() {
        List<Board> boards = boardRepository.findByReportedIsTrue();
        List<BoardSimpleDto> boardsDto = new ArrayList<>();
        boards.stream().forEach(i -> boardsDto.add(new BoardSimpleDto().toDto(i)));
        return boardsDto;
    }

    @Transactional(readOnly = true)
    public BoardSimpleDto unlockBoard(int id) {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        if(!board.isReported()) {
            throw new NotReportedException();
        }
        board.setReported(false);
        boardReportRepository.deleteAllByReportedBoardId(id);
        return new BoardSimpleDto().toDto(board);
    }
}

