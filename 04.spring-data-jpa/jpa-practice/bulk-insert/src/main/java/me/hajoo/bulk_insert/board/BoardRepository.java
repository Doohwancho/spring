package me.hajoo.bulk_insert.board;

import me.hajoo.bulk_insert.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
