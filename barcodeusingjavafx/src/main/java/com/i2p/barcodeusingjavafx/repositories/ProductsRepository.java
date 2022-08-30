package com.i2p.barcodeusingjavafx.repositories;

import com.i2p.barcodeusingjavafx.models.Products;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductsRepository extends CrudRepository<Products, String> {
    @Query("select reference from Products p where p.name=?1")
    String findProductsReferenceByName(String name);


    @Query("select name from Products p where p.reference=?1")
    String findProductNameByReference(String reference);


    public static final String FIND_NAMES = "SELECT name FROM Products";
    @Query(value = FIND_NAMES, nativeQuery = true)
    public List<String> findNameOfProducts();


    public static final String FIND_REFERENCES = "SELECT reference FROM Products";
    @Query(value = FIND_REFERENCES, nativeQuery = true)
    public List<String> findReferenceOfProducts();



    public static final String FIND_REFERENCES_NAMES = "SELECT reference, name FROM Products";
    @Query(value = FIND_REFERENCES_NAMES, nativeQuery = true)
    public List<String> findReferenceNameOfProducts();

    @Query("select name from Products pro where pro.category=?1")
    List<String> findProductNamesByCategoryId(String catId);


}
