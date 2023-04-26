---\
Goal


1. Modularize rules of spring-data-jpa
2. Modularize best practices of JPA



---\
fields in persistence framework


a. jpa\
b. spring-data-jpa\
c. queryDSL
d. steal: jpashop from 김영한 책\
e. steal: jpa-best-practices-cheese10yun\
f. jpa-best-practices\
g. steal: JY_Commerce\
h. steal: jpa_quickstart




---\
Todos


a-1. 객체와 테이블 매핑: @Entity :white_check_mark:\
a-2. 객체와 테이블 매핑: @Table :white_check_mark:\
a-3. 기본 키 매핑: @Id :white_check_mark:\
a-4. 필드와 컬럼 매핑: @Column :white_check_mark:\
a-5. 필드와 컬럼 매핑: @GeneratedValue :white_check_mark:\
a-6. 필드와 컬럼 매핑: @Enumerated :white_check_mark:\
a-7. 연관관계 매핑: @OneToOne :white_check_mark:\
a-8. 연관관계 매핑: @OneToMany :white_check_mark:\
a-9. 연관관계 매핑: @ManyToOne :white_check_mark:\
a-10. 연관관계 매핑: @ManyToMany - 사용 지양 :white_check_mark:\
a-11. n+1 problem :white_check_mark:\
a-12. 단방향, 양방향 :white_check_mark:\
a-13. 연관관계의 주인 :white_check_mark:\
a-14. 식별자 매핑 어노테이션 :white_check_mark:\
a-15. 상속관계 :white_check_mark:\
a-16. persistence context: entity lifecycle :white_check_mark:\
a-17. persistence context: pros - 1차 캐시 :white_check_mark:\
a-18. persistence context: pros - LAZY Loading :white_check_mark:\
a-19. persistence context: pros - transactional write-behind, buffer기능 :white_check_mark:\
a-20. persistence context: pros - dirty checking :white_check_mark:\
a-21. persistence context: flush() :white_check_mark:\
a-22. persistence context: 준영속 상태(detached state) :white_check_mark:\
a-23. CASCADE :white_check_mark:\
a-24. join vs fetchJoin :white_check_mark:\
a-25. OSIV(open session in view) :white_check_mark:


c-1. init queryDSL :white_check_mark:\
c-2. syntax - where condition :white_check_mark:\
c-3. syntax - and :white_check_mark:\
c-4. syntax - fetch results :white_check_mark:\
c-5. syntax - orderBy :white_check_mark:\
c-6. syntax - paging :white_check_mark:\
c-7. syntax - groupBy :white_check_mark:\
c-8 syntax - join :white_check_mark:\
c-9 syntax - subQuery :white_check_mark:\
c-10 syntax - case :white_check_mark:\
c-11 syntax - CONSTANT :white_check_mark:\
c-12. syntax - string concat :white_check_mark:\
c-13. syntax - projection :white_check_mark:\
c-14. syntax - 동적 쿼리 :white_check_mark:\
c-15. syntax - 수정, 삭제 처리 :white_check_mark:\
c-16. syntax - sql 함수 호출 :white_check_mark:\
c-17. Spring Data Page, Pageable을 통한 Pagination 구현 :white_check_mark:


d-1. jpashop initialize :white_check_mark:\
d-2. requirements -> erd :white_check_mark:\
d-3. jpql 조건에 따른 처리 :white_check_mark:\
d-4. Criteria 구경하기 :white_check_mark:\
d-5. java generics used for controller layer Response Object :white_check_mark:\
d-6. flush() :white_check_mark:\
d-7. controller에서 파라미터로 받은 객체는 영속성 객체가 아니다 :white_check_mark:\
d-8. update시 팁: merge 보다는 find() 후 setter 하고 냅둬 :white_check_mark:\
d-9. erd -> entity modeling :white_check_mark:



e-1. init jpa-best-practices-cheese-yun :white_check_mark:\
e-2. Coupon:Order(주인) = 1:1, 양방향 :white_check_mark:\
e-3. Paging(+ queryDSL on service) :white_check_mark:\
e-4. Querydsl를 이용해서 Repository 확장하기 :white_check_mark:\
e-5. 비밀번호 요구사항을 DDD하게 처리 :white_check_mark:\
e-6. @Setter 대체 방법 :white_check_mark:\
e-7. properties 설정값 가져오기 :white_check_mark:


f-1. paging & sorting :white_check_mark:\
f-2. bulk insert :white_check_mark:\
f-3. dynamic-insert-update :white_check_mark:\
f-4. rollbackFor :white_check_mark:


g-1. requirements :white_check_mark:\
g-2. erd modeling :white_check_mark:\
g-3. entity modeling :white_check_mark:


h-1. Chapter01.jdbc -> mybatis -> hibernate(orm) 변천사 :white_check_mark:\
h-2. N:1 relations에서 jpql 수동 sql query :white_check_mark:\
h-3. N:1 양방향 관계시 N을 먼저 다 지운 후, 1을 지워야 한다 :white_check_mark:\
h-4. 1:1 단방향/양방향 관계 jpa 처리 :white_check_mark:\
h-5. N:M 단방향/양방향 relations 유무에 따른 처리 :white_check_mark:\
h-6. named query :white_check_mark:\
h-7. 묵시적 join in jpql :white_check_mark:\
h-8. subquery :white_check_mark:\
h-9. pagination read query :white_check_mark:\
h-10. jpql의 criteria를 이용한 where절 조건검색 :white_check_mark:

---\
Reference

1. [spring-jpa best practices by cheese10yun 뜯어보기](https://github.com/cheese10yun/spring-jpa-best-practices)
2. [sample code](https://github.dev/TIL-Repo/jpa-tutorial)
3. [인프런 실전 dsl강좌 code by 김영한](https://github.dev/freespringlecture/jpashop-querydsl)
4. [spring jpa 정리 시리즈](https://ws-pace.tistory.com/category/Web/%EC%8A%A4%ED%94%84%EB%A7%81%20JPA%20%EC%A0%95%EB%A6%AC%20%EC%8B%9C%EB%A6%AC%EC%A6%88)
5. [단방향, 양방향](https://jeong-pro.tistory.com/231)
6. [김영한 jpa 책 요약](https://ict-nroo.tistory.com/category/ICT%20Eng/JPA?page=3)
7. [jpa 상속관계](https://ict-nroo.tistory.com/128)
8. [jpa 프로그래밍 기본기 다지기](https://ryan-han.com/post/dev/jpa_basics/)
9. [inflearn 실전 querydsl 강좌](https://github.com/freespringlecture/jpashop-querydsl)
10. [JY_Commerce](https://github.com/galid1/JY_Commerce)
11. [book - jpa_quickstart](http://www.yes24.com/Product/Goods/92287236)

x-x. [amigoscode spring-data-jpa-course 뜯어보기](https://github.com/amigoscode/spring-data-jpa-course)
