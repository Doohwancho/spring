package com.cho.basic.fetchJoin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("SELECT distinct t FROM Team t join t.members")
    public List<Team> findAllWithMemberUsingJoin();

    @Query("SELECT distinct t FROM Team t join fetch t.members")
    public List<Team> findAllWithMemberUsingFetchJoin();

    @Query("SELECT distinct t FROM Team t join t.members m where m.name = :memberName")
    public List<Team> findByMemberNameWithMemberUsingJoin(String memberName);
}
