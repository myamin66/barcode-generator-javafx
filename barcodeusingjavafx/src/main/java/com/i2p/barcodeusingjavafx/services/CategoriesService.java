package com.i2p.barcodeusingjavafx.services;
import com.i2p.barcodeusingjavafx.models.Categories;
import com.i2p.barcodeusingjavafx.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;
    public List<Categories> getAllProducts(){
        List<Categories> categories = new ArrayList<>();
        categoriesRepository.findAll().forEach(category -> {
            categories.add(category);
        });
        return categories;
    }

    public List<Categories> getAllCategories(){
        return categoriesRepository.findAll();
    }

    public List<Categories> findAll() {
        return categoriesRepository.findAll();
    }

    public Optional<Categories> findById(String id) {
        return categoriesRepository.findById(id);
    }

    /**
     * It will return the name of all categories.
     * @return
     */
    public List<String> findNameOfCategory() {
        return categoriesRepository.findNameOfCategory();
    }

    public List<Categories> findCategoriesByName(String name) {
        return categoriesRepository.findCategoriesByName(name);
    }

    public String findCategoryIdByName(String name){
        return categoriesRepository.findCategoryIdByName(name);
    }
}
