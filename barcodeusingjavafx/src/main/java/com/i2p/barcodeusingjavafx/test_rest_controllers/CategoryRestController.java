package com.i2p.barcodeusingjavafx.test_rest_controllers;

import com.i2p.barcodeusingjavafx.models.Categories;
import com.i2p.barcodeusingjavafx.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryRestController {

    @Autowired
    private CategoriesService categoriesService;
    @RequestMapping(value = "/getAllCategories", method = RequestMethod.GET)
    private List<Categories> getAllCategories(){
        return categoriesService.findAll();
    }

    @RequestMapping(value = "/getCategoryById/{id}", method = RequestMethod.GET)
    public Optional<Categories> findByIds(@PathVariable String id) {
        return categoriesService.findById(id);
    }

    @GetMapping("/getCategoriesNames")
    public List<String> findCategoriesNames() {
        return categoriesService.findNameOfCategory();
    }

    @GetMapping("/getAllCategoriesByName/{name}")
    public List<Categories> getAllCategoriesByName(@PathVariable String name){
        return categoriesService.findCategoriesByName(name);
    }
    @GetMapping("/getCategoryIdByName/{name}")
    public String getAllCategoryIdByName(@PathVariable String name){
        return categoriesService.findCategoryIdByName(name);
    }
}
