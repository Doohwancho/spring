package com.cho.basic.NPlus1.repository;

import com.cho.basic.NPlus1.vo.Academy;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by jojoldu@gmail.com on 2017. 7. 21.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

public interface AcademyRepository extends JpaRepository<Academy, Long> {

    /**
     * 1. join fetch를 통한 조회
     */
    @Query("select a from Academy a join fetch a.subjects")
    List<Academy> findAllJoinFetch();

    /**
     * 2. @EntityGraph
     */
    @EntityGraph(attributePaths = "subjects")
    @Query("select a from Academy a")
    List<Academy> findAllEntityGraph();

    /**
     * 3. join fetch + distinct 를 통한 조회
     */
    @Query("select DISTINCT a from Academy a join fetch a.subjects")
    List<Academy> findAllJoinFetchDistinct();

    /**
     * 4. @EntityGraph + distinct 를 통한 조회
     */

    @EntityGraph(attributePaths = "subjects")
    @Query("select DISTINCT a from Academy a")
    List<Academy> findAllEntityGraphDistinct();


    /**
     * 5. Academy+Subject+Teacher를 join fetch로 조회
     */
    @Query("select distinct a from Academy a join fetch a.subjects s join fetch s.teacher")
    List<Academy> findAllWithTeacher();

    /**
     * 6. Academy+Subject+Teacher를 @EntityGraph 로 조회
     */
    @EntityGraph(attributePaths = {"subjects", "subjects.teacher"})
    @Query("select DISTINCT a from Academy a")
    List<Academy> findAllEntityGraphWithTeacher();


}
