package cho.community.service.category;

import cho.community.dto.category.CategoryCreateRequest;
import cho.community.dto.category.CategoryDto;
import cho.community.entity.category.Category;
import cho.community.exception.CategoryNotFoundException;
import cho.community.repository.category.CategoryRepository;
import cho.community.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<CategoryDto> findAll() {
        List<Category> categories = categoryRepository.findAllOrderByParentIdAscNullsFirstCategoryIdAsc();
        return CategoryDto.toDtoList(categories);
    }

    @Transactional
    public void create(CategoryCreateRequest req) {
        Category parent = Optional.ofNullable(req.getParentId())
                .map(id -> categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new))
                .orElse(null);
        categoryRepository.save(new Category(req.getName(), parent));
    }

    @Transactional
    public void delete(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        categoryRepository.delete(category);
    }
}
