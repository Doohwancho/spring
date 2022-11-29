package me.hajoo.dynamic_insert_update.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity @Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String columnA;
    private String columnB;
    private String columnC;
    private String columnD;
    private String columnE;
    private String columnF;
    private String columnG;
    private String columnH;
    private String columnI;
    private String columnJ;
    private String columnK;
    private String columnL;
    private String columnM;

    public User(String columnA) {
        this.columnA = columnA;
    }

    public void changeColumnA(String columnA) {
        this.columnA = columnA;
    }
}
