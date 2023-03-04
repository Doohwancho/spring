package com.example.reset;

import com.google.common.base.CaseFormat;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.metamodel.EntityType;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * RestAssured test시 persistent context에 cache 지우기 + Entity의 id 초기화 코드
 *
 * prerequisites: guava library
 */
@Component
public class DatabaseCleanup implements InitializingBean {
    @PersistenceContext
    private EntityManager entityManager;
    private List<String> tableNames;

    @Value("${spring.datasource.platform}")
    private String databasePlatform;

    @Override
    public void afterPropertiesSet() {
        final Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities(); //jpa에 있는 @Entity 전부 가져온다.
        tableNames = entities.stream()
                .filter(e -> isEntity(e) && hasTableAnnotation(e)) //@Entity 어노테이션 붙어있고, @Table 어노테이션 붙어있으면,
                .map(e -> {
                    String tableName = e.getJavaType().getAnnotation(Table.class).name();
                    return tableName.isBlank() ? CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getName()) : tableName; //담을 때, 대문자 camel case에서, 소문자 snake_case 로 바꿔준다.
                })
                .collect(Collectors.toList()); //테이블 이름을 전부 리스트에 담는다.

        final List<String> entityNames = entities.stream()
                .filter(e -> isEntity(e) && !hasTableAnnotation(e)) ////@Entity 어노테이션 붙어있는데 @Table 어노테이션이 없으면,
                .map(e -> CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getName())) //담을 때, 대문자 camel case에서, 소문자 snake_case 로 바꿔준다.
                .collect(Collectors.toList()); //테이블 이름을 전부 리스트에 담는다.

        tableNames.addAll(entityNames); //이 두 리스트를 더해준다.
    }

    private boolean isEntity(final EntityType<?> e) {
        return null != e.getJavaType().getAnnotation(Entity.class);
    }

    private boolean hasTableAnnotation(final EntityType<?> e) {
        return null != e.getJavaType().getAnnotation(Table.class);
    }

    @Transactional
    public void execute() {
        entityManager.flush();

        if(databasePlatform.equals("h2")) {
            resetH2();
            return;
        }
        if(databasePlatform.equals("mysql")) {
            resetMysql();
            return;
        }
    }

    private void resetH2() {
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate(); //해당 테이블 지우려고 하는데, 다른 테이블과 FK로 연결되있으면 잘 안지워지니까, 그런걸 무시하는 코드

        for (final String tableName : tableNames) {
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate(); //테이블 비우고
            entityManager.createNativeQuery("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1").executeUpdate(); //해당 테이블 id auto_increment 된 것을 다시 1로 초기화해주는 것
        }

        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }

    private void resetMysql() {
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate(); //해당 테이블 지우려고 하는데, 다른 테이블과 FK로 연결되있으면 잘 안지워지니까, 그런걸 무시하는 코드

        for (final String tableName : tableNames) {
            entityManager.createNativeQuery("TRUNCATE " + tableName).executeUpdate(); //테이블 비우고
            entityManager.createNativeQuery("ALTER TABLE " + tableName + " AUTO_INCREMENT = 1").executeUpdate(); //해당 테이블 id auto_increment 된 것을 다시 1로 초기화해주는 것
        }

        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
    }
}