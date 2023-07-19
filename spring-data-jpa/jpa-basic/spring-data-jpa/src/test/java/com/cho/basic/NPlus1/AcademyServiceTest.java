package com.cho.basic.NPlus1;

import com.cho.basic.NPlus1.repository.AcademyRepository;
import com.cho.basic.NPlus1.repository.TeacherRepository;
import com.cho.basic.NPlus1.service.AcademyService;
import com.cho.basic.NPlus1.vo.Academy;
import com.cho.basic.NPlus1.vo.Subject;
import com.cho.basic.NPlus1.vo.Teacher;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by jojoldu@gmail.com on 2017. 7. 21.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AcademyServiceTest {

    @Autowired
    private AcademyRepository academyRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AcademyService academyService;

    @AfterAll
    public void cleanAll() {
        academyRepository.deleteAll();
        teacherRepository.deleteAll();
    }

    @BeforeAll
    public void setup() {
        //Teacher:Subject = 1:N, 단방향
        //Subject:Academy = N:1, 양방향

        //Teacher: 1개
        //Subject: "자바웹개발" 3개 + "파이썬자동화" 3개 = 총 6개
        //Academy: 3개 (academy 한개에 "자바웹개발", "파이썬자동화" Subject 두개씩 들어감)

        List<Academy> academies = new ArrayList<>();
        Teacher teacher = teacherRepository.save(new Teacher("선생님"));

        for(int i=0;i<3;i++){
            Academy academy = Academy.builder()
                    .name("강남스쿨"+i)
                    .build();

            academy.addSubject(Subject.builder().name("자바웹개발" + i).teacher(teacher).build());
            academy.addSubject(Subject.builder().name("파이썬자동화" + i).teacher(teacher).build()); // Subject를 추가
            academies.add(academy);
        }

        academyRepository.saveAll(academies);
    }

    /**
     * 문제: 쿼리가 1+N 번 나감
     *
     *
     * Hibernate:
     *     select
     *         academy0_.id as id1_0_,
     *         academy0_.name as name2_0_
     *     from
     *         academy academy0_
     * 아카데미 땡겨오는데,
     *
     * 이후, proxy 객체 건드릴 때, LAZY_LOADING으로 관련 객체 다 가져옴.
     *
     * 얘 한번에, (1)
     * Hibernate:
     *     select
     *         academy0_.id as id1_0_,
     *         academy0_.name as name2_0_
     *     from
     *         academy academy0_
     *
     * 아래꺼 3번(N번) 돌아감.(1+N)
     *
     * Hibernate:
     *     select
     *         subjects0_.academy_id as academy_3_13_0_,
     *         subjects0_.id as id1_13_0_,
     *         subjects0_.id as id1_13_1_,
     *         subjects0_.academy_id as academy_3_13_1_,
     *         subjects0_.name as name2_13_1_,
     *         subjects0_.teacher_id as teacher_4_13_1_
     *     from
     *         subject subjects0_
     *     where
     *         subjects0_.academy_id=?
     *
     *
     *  이 후, update까지 3번 돈다.
     *
     *  Hibernate:
     *     update
     *         subject
     *     set
     *         academy_id=null
     *     where
     *         academy_id=?
     */
    @Test
    @DisplayName("1. Academy여러개를_조회시_Subject가_N1_쿼리가발생한다")
    public void Academy여러개를_조회시_Subject가_N1_쿼리가발생한다() throws Exception {
        //given
        List<String> subjectNames = academyService.findAllSubjectNames();

        //then
        assertThat(subjectNames.size(), is(3)); //subject는 총 6개나, 3개뿐이 없네?
    }

    /**
     * fetchJoin
     * 연관된 애들을 쿼리 한번에 모두 땡겨오는 방법.
     *
     * 한방에 땡겨온다는 장점이 있으나, 불필요한 쿼리문이 추가된다는 단점.
     *
     *
     * Hibernate:
     *     select
     *         academy0_.id as id1_0_0_,
     *         subjects1_.id as id1_13_1_,
     *         academy0_.name as name2_0_0_,
     *         subjects1_.academy_id as academy_3_13_1_,
     *         subjects1_.name as name2_13_1_,
     *         subjects1_.teacher_id as teacher_4_13_1_,
     *         subjects1_.academy_id as academy_3_13_0__,
     *         subjects1_.id as id1_13_0__
     *     from
     *         academy academy0_
     *     inner join
     *         subject subjects1_
     *             on academy0_.id=subjects1_.academy_id
     *
     *  맨 처음 query로 떙겨올 때, inner join으로 subject랑 academy 합쳐서 땡겨온다.
     *
     */
    @Test
    @DisplayName("2. Academy여러개를_joinFetch로_가져온다")
    public void Academy여러개를_joinFetch로_가져온다() throws Exception {
        //given
//        List<Academy> academies = academyRepository.findAllJoinFetch();
        List<String> subjectNames = academyService.findAllSubjectNamesByJoinFetch();

        //then
//        assertThat(academies.size(), is(6)); // 다 땡겨 오니까 6개 조회.
        assertThat(subjectNames.size(), is(6)); // 다 땡겨 오니까 6개 조회.
    }

    /**
     * yet
     */
    @Test
    public void Academy여러개를_EntityGraph로_가져온다() throws Exception {
        //given
        List<Academy> academies = academyRepository.findAllEntityGraph();
        List<String> subjectNames = academyService.findAllSubjectNamesByEntityGraph();

        //then
        assertThat(academies.size(), is(3));
        assertThat(subjectNames.size(), is(3));
    }

    /**
     * yet
     */
    @Test
    public void Academy여러개를_distinct해서_가져온다 () throws Exception {
        //given
        System.out.println("조회 시작");
        List<Academy> academies = academyRepository.findAllJoinFetchDistinct();

        //then
        System.out.println("조회 끝");
        assertThat(academies.size(), is(3));
    }

    /**
     * yet
     */
    @Test
    public void Academy_Subject_Teacher를_한번에_가져온다() throws Exception {
        //given
        System.out.println("조회 시작");
        List<Teacher> teachers = academyRepository.findAllWithTeacher().stream()
                .map(a -> a.getSubjects().get(0).getTeacher())
                .collect(Collectors.toList());

        //then
        System.out.println("조회 끝");
        assertThat(teachers.size(), is(3));
    }

    /**
     * yet
     */
    @Test
    public void Academy_Subject_Teacher를_EntityGraph한번에_가져온다() throws Exception {
        //given
        System.out.println("조회 시작");
        List<Teacher> teachers = academyRepository.findAllEntityGraphWithTeacher().stream()
                .map(a -> a.getSubjects().get(0).getTeacher())
                .collect(Collectors.toList());

        //then
        System.out.println("조회 끝");
        assertThat(teachers.size(), is(3));
        assertThat(teachers.get(0).getName(), is("선생님"));

    }
}
