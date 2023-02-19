---\
Goal


1. Modularize rules of spring-data-jpa
2. Modularize best practices of JPA



---\
fields in persistence framework


a. jpa\
b. spring-data-jpa\
c. queryDSL
d. steal: jpashop




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
a-24. join vs fetchJoin :white_check_mark:



b-1. paging & sorting\
b-3. bulk insert :white_check_mark:\
b-4. dynamic-insert-update :white_check_mark:\
b-5. rollbackFor :white_check_mark:\


d-1. jpashop initialize :white_check_mark:\
d-2. entity modeling :white_check_mark:






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

x-x. [amigoscode spring-data-jpa-course 뜯어보기](https://github.com/amigoscode/spring-data-jpa-course)
