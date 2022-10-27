package cho.community.service.report;

import cho.community.dto.report.BoardReportRequest;
import cho.community.dto.report.BoardReportResponse;
import cho.community.dto.report.UserReportRequest;
import cho.community.dto.report.UserReportResponse;
import cho.community.dto.user.UserDto;
import cho.community.entity.board.Board;
import cho.community.entity.report.BoardReport;
import cho.community.entity.report.UserReport;
import cho.community.entity.user.User;
import cho.community.exception.AlreadyReportException;
import cho.community.exception.BoardNotFoundException;
import cho.community.exception.MemberNotFoundException;
import cho.community.exception.type.NotSelfReportException;
import cho.community.repository.board.BoardRepository;
import cho.community.repository.report.BoardReportRepository;
import cho.community.repository.report.UserReportRepository;
import cho.community.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ReportService {

    public final BoardReportRepository boardReportRepository;
    public final UserReportRepository userReportRepository;
    public final UserRepository userRepository;
    public final BoardRepository boardRepository;

    @Transactional
    public UserReportResponse reportUser(User reporter, UserReportRequest req) {
        User reportedUser = userRepository.findById(req.getReportedUserId()).orElseThrow(MemberNotFoundException::new);

        if (reporter.getId() == req.getReportedUserId()) {
            // 자기 자신을 신고한 경우
            throw new NotSelfReportException();
        }

        if (userReportRepository.findByReporterIdAndReportedUserId(reporter.getId(), req.getReportedUserId()) == null) {
            // 신고 한 적이 없다면, 테이블 생성 후 신고 처리 (ReportedUser의 User테이블 boolean 값 true 변경 ==> 신고처리)
            UserReport userReport = new UserReport(reporter.getId(), reportedUser.getId(), req.getContent());
            userReportRepository.save(userReport);

            if (userReportRepository.findByReportedUserId(req.getReportedUserId()).size() >= 3) {
                // 신고 수 10 이상일 시 true 설정
                reportedUser.setReported(true);
            }

            UserReportResponse res = new UserReportResponse(userReport.getId(), UserDto.toDto(reportedUser), req.getContent(), userReport.getCreateDate());
            return res;
        } else {
            // 이미 신고를 했다면 리턴
            throw new AlreadyReportException();
        }
    }

    @Transactional
    public BoardReportResponse reportBoard(User reporter, BoardReportRequest req) {
        Board reportedBoard = boardRepository.findById(req.getReportedBoardId()).orElseThrow(BoardNotFoundException::new);

        if (reporter.getId() == reportedBoard.getUser().getId()) {
            throw new NotSelfReportException();
        }

        if (boardReportRepository.findByReporterIdAndReportedBoardId(reporter.getId(), req.getReportedBoardId()) == null) {
            // 신고 한 적이 없다면, 테이블 생성 후 신고 처리
            BoardReport boardReport = new BoardReport(reporter.getId(), reportedBoard.getId(), req.getContent());
            boardReportRepository.save(boardReport);


            if (boardReportRepository.findByReportedBoardId(req.getReportedBoardId()).size() >= 10) {
                // 신고 수 10 이상일 시 true 설정
                reportedBoard.setReported(true);
            }

            BoardReportResponse res = new BoardReportResponse(boardReport.getId(), req.getReportedBoardId(), req.getContent(), boardReport.getCreateDate());
            return res;
        } else {
            throw new AlreadyReportException();
        }
    }


}