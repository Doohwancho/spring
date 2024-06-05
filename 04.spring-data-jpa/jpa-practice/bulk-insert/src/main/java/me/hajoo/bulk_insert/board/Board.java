package me.hajoo.bulk_insert.board;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "BOARD_SEQ_GEN",
        sequenceName = "BOARD_SEQ",
        initialValue = 1,
        allocationSize = 60000
)
@Getter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "BOARD_SEQ_GEN")
    private Long id;

    private String title;
    private String content;

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
