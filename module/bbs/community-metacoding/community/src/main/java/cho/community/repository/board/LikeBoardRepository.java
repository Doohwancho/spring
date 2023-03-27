package cho.community.repository.board;

import cho.community.entity.board.Board;
import cho.community.entity.board.LikeBoard;
import cho.community.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeBoardRepository extends JpaRepository<LikeBoard, Integer> {

    LikeBoard findByBoardAndUser(Board board, User user);
}
