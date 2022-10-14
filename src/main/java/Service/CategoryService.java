package Service;

import Model.Category;
import Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.getAll();
    }
    public Optional<Category> getCategory (int id){
        return categoryRepository.getCategory(id);
    }

    public Category save(Category category){
        if(category.getId()==null){
            if (category.getName().length()<=45 && category.getDescription().length()<=250){
                return categoryRepository.save(category);
            } else {
                return category;
            }

        } else {
            Optional<Category> categoryEncontrada = categoryRepository.getCategory(category.getId());
            if(categoryEncontrada.isEmpty()){
                return categoryRepository.save((category));
            } else {
                return category;
            }
        }
    }

    public Category update(Category category){
        if(category.getId()!=null){
            Optional<Category> categoryEncontrada = categoryRepository.getCategory(category.getId());
            if (!categoryEncontrada.isEmpty()){
                if (category.getDescription()!=null){
                    categoryEncontrada.get().setDescription(category.getDescription());
                }
                if(category.getName()!=null){
                    categoryEncontrada.get().setName(category.getName());
                }
                return categoryRepository.save(categoryEncontrada.get());
            }
        }
        return category;
    }

}
