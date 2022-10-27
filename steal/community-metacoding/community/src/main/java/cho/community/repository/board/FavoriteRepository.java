package cho.community.repository.board;

import cho.community.entity.board.Board;
import cho.community.entity.board.Favorite;
import cho.community.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    Favorite findFavoriteByBoard(Board board);
    Favorite findByBoardAndUser(Board board, User user);
    List<Favorite> findAllByUser(User user);
}
