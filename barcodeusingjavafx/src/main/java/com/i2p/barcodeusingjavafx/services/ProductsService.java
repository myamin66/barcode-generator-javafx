package com.i2p.barcodeusingjavafx.services;
import com.i2p.barcodeusingjavafx.BarcodeController;
import com.i2p.barcodeusingjavafx.models.Products;
import com.i2p.barcodeusingjavafx.repositories.CategoriesRepository;
import com.i2p.barcodeusingjavafx.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService{
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private BarcodeController barcodeController;

    public List<Products> getAllProducts(){
        List<Products> productsList = new ArrayList<>();
        productsRepository.findAll().forEach(product -> {
            productsList.add(product);
        });
        return productsList;
    }

    public String getReferenceByName(String name){
        return productsRepository.findProductsReferenceByName(name);
    }

    public String getProductNameByReference(String reference){
        return productsRepository.findProductNameByReference(reference);
    }

    public List<String> getProductsNames(){
        return productsRepository.findNameOfProducts();
    }

    public Optional<Products> findProductById(String id) {
        return productsRepository.findById(id);
    }

    public String findProductsReferenceByName(String name) {
        return productsRepository.findProductsReferenceByName(name);
    }

    public List<String> findNameOfProducts() {
        return productsRepository.findNameOfProducts();
    }

    public List<String> findReferenceOfProducts() {
        return productsRepository.findReferenceOfProducts();
    }

    public List<String> findReferenceNameOfProducts() {
        return productsRepository.findReferenceNameOfProducts();
    }

    public List<String> findProductNamesByCatId(String catId){
        return productsRepository.findProductNamesByCategoryId(catId);
    }


}
