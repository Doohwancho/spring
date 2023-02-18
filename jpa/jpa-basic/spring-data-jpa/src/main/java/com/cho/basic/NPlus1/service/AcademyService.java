package com.cho.basic.NPlus1.service;

import com.cho.basic.NPlus1.repository.AcademyRepository;
import com.cho.basic.NPlus1.vo.Academy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jojoldu@gmail.com on 2017. 7. 22.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Service
@Slf4j
public class AcademyService {

    private AcademyRepository academyRepository;

    public AcademyService(AcademyRepository academyRepository) {
        this.academyRepository = academyRepository;
    }

    //case1) Academy여러개를_조회시_Subject가_N1_쿼리가발생한다
    @Transactional
    public List<String> findAllSubjectNames(){
        return extractSubjectNames(academyRepository.findAll());
    }

    //case2) Academy여러개를_joinFetch로_가져온다
    @Transactional
    public List<String> findAllSubjectNamesByJoinFetch(){
        return extractSubjectNames(academyRepository.findAllJoinFetch());
    }

    @Transactional
    public List<String> findAllSubjectNamesByEntityGraph() {
        return extractSubjectNames(academyRepository.findAllEntityGraph());
    }

    @Transactional
    public List<String> findAllSubjectNamesByJoinFetchDistinct(){
        return extractSubjectNames(academyRepository.findAllJoinFetchDistinct());
    }

    @Transactional
    public List<String> findAllSubjectNamesByEntityGraphDistinct() {
        return extractSubjectNames(academyRepository.findAllEntityGraphDistinct());
    }
    /**
     * Lazy Load를 수행하기 위해 메소드를 별도로 생성
     */
    private List<String> extractSubjectNames(List<Academy> academies){
        log.info(">>>>>>>>[모든 과목을 추출한다]<<<<<<<<<");
        log.info("Academy Size : {}", academies.size());

        return academies.stream()
                .map(a -> a.getSubjects().get(0).getName())
                .collect(Collectors.toList());
    }
}