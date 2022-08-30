package com.i2p.barcodeusingjavafx.test_rest_controllers;

import com.i2p.barcodeusingjavafx.models.Products;
import com.i2p.barcodeusingjavafx.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductsRestControllers {
    @Autowired
    private ProductsService productsService;
    @GetMapping("/getAllProducts")
    public List<Products> getAllProducts(){
        return productsService.getAllProducts();
    }

    @RequestMapping(value = "/getProductById/{id}", method = RequestMethod.GET)
    public Optional<Products> findByIds(@PathVariable String id) {
        return productsService.findProductById(id);
    }

    @RequestMapping(value = "/getProductReferenceByName/{name}", method = RequestMethod.GET)
    public String findProductReferenceByName(String name) {
        return productsService.findProductsReferenceByName(name);
    }

    @GetMapping("/getProductsNames")
    public List<String> findProductsNames() {
        return productsService.findNameOfProducts();
    }


    @GetMapping("/getReferencesOfProducts")
    public List<String> findReferences() {
        return productsService.findReferenceOfProducts();
    }

    @GetMapping("/getProductNamesByCategoryId/{catId}")
    public List<String> getAllProductsIdByName(@PathVariable String catId){
        return productsService.findProductNamesByCatId(catId);
    }

    @RequestMapping(value = "/findProductNameWithReference")
    public List<String> findProductNameReference() {
        return productsService.findReferenceNameOfProducts();
    }
}


