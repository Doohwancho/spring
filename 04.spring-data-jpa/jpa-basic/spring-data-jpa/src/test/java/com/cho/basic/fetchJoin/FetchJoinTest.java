package com.cho.basic.fetchJoin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class FetchJoinTest {
    @Autowired
    private TeamService teamService;

    @BeforeEach
    public void init(){
        teamService.initialize();
    }

    /**
     * 에러!
     *
     * 가장 자주 본다는 LazyInitializationException - failed to lazily initialize a collection of role: com.cho.basic.fetchJoin.Team.members, could not initialize proxy - no Session
     *
     * break point찍고 debugging 해 보면, memberUsingJoin안에 0,1번째 Member 객체에 members에서
     * LazyInitializationException 터지면서 join 했는데, 안했다고 나옴.
     * 왜?
     * 그냥 join은, sql에는 join거는데, persitence context안 entity 사이에 까진 join걸지 않음.
     *
     * 1. TeamService에 initialize()에서 team객체에 member객체 넣어주고, save(team); 하는데,
     * 2. teamRepository.save(team1); 할 때, team을 영속성 컨텍스트에 가져와 초기화 시키지만, join걸린 Member까지는 영속성 컨텍스트 단에서 초기화 시키진 않음.
     * 3. toString()으로 아직 초기화되지 않은 members에 접근하면서 LazyInitializationException 발생
     * (실제로 Team에 @ToString(exclude="members")를 설정하게 되면 members에 접근하지 않게 되고 LazyInitializationException 또한 발생하지 않게 됩니다.)
     *
     *
     * Hibernate:
     *     select
     *         distinct team0_.id as id1_12_,
     *         team0_.name as name2_12_
     *     from
     *         team team0_
     *     inner join
     *         member members1_
     *             on team0_.id=members1_.team_id
     *
     *  team.members는 아직 초기화가 안되서 거즘 비어있는 proxy 객체라는거네? 건드리면 LazyInitializationException 나고.
     */
    @Test
    @Disabled
    public void joinTest() {
        List<Team> memberUsingJoin = teamService.findAllWithMemberUsingJoin();
        //break point here and try debug!
        System.out.println(memberUsingJoin); //error! - LazyInitializationException
    }

    /**
     *
     * fetchJoin을 사용하면, 그냥 join처럼 inner join하긴 하는데, member에 엮긴 애들까지 다 끌어오네?
     * 그냥 join은 실제 sql시에만 join을 날리는 거라면,
     * fetchJoin은 영속성 컨텍스트에서 entity 가져올 때, join해서 보관한다는 거잖아?
     *
     * Hibernate:
     *     select
     *         distinct team0_.id as id1_12_0_,
     *         members1_.id as id1_5_1_,
     *         team0_.name as name2_12_0_,
     *         members1_.age as age2_5_1_,
     *         members1_.name as name3_5_1_,
     *         members1_.team_id as team_id4_5_1_,
     *         members1_.team_id as team_id4_5_0__,
     *         members1_.id as id1_5_0__
     *     from
     *         team team0_
     *     inner join
     *         member members1_
     *             on team0_.id=members1_.team_id
     */

    @Test
    public void fetchJoinTest() {
        List<Team> memberUsingFetchJoin = teamService.findAllWithMemberUsingFetchJoin();
        //break point
        System.out.println(memberUsingFetchJoin); //[Team(id=1, name=team1, members=[Member(id=1, name=team1member1, age=1), Member(id=2, name=team2member2, age=2), Member(id=3, name=team3member3, age=3)]), Team(id=2, name=team2, members=[Member(id=4, name=team2member4, age=4), Member(id=5, name=team2member5, age=5)])]
    }

    /**
     * 무조건 일반 join말고 fetch join써야하나?
     *
     * 아니?
     *
     * 아래 경우 처럼, team2member4라는 이름을 가진 member가 속해있는 팀 조회할 땐, 일반 join이 더 효과적.
     * 왜?
     *
     * 연관 관계가 있는 Entity가 쿼리 검색 조건에는 필요하지만 실제 데이터는 필요하지 않은 상황임.
     *  debugging해 보면, Team 객체에 name은 가져와있는데 member는 proxy 객체인 상황에서, member 안건드리고 name만 필요하면, fetchJoin말고 join 써도 된다.
     *
     *
     */
    @Test
    @Disabled
    public void joinConditionTest() {
        List<Team> memberUsingJoin = teamService.findByMemberNameWithMemberUsingJoin("team2member4");
        //break point
        System.out.println(memberUsingJoin);
    }
}
