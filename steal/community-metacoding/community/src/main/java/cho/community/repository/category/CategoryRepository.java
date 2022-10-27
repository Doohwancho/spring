package cho.community.repository.category;


import cho.community.entity.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("SELECT c FROM Category c LEFT JOIN c.parent p ORDER BY p.id ASC NULLS FIRST, c.id ASC") //부모 카테고리 아이디 기준 오름차순으로 정렬하고, NULL 값을 처음으로 처리한 후, 자신의 카테고리를 기준으로 오름차순 정렬 조회
    List<Category> findAllOrderByParentIdAscNullsFirstCategoryIdAsc();
}
