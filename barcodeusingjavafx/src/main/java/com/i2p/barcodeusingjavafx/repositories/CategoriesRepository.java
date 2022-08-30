package com.i2p.barcodeusingjavafx.repositories;

import com.i2p.barcodeusingjavafx.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, String> {
    public static final String FIND_NAMES = "SELECT name FROM Categories";
    @Query(value = FIND_NAMES, nativeQuery = true)
    public List<String> findNameOfCategory();


    @Query("select cat from Categories cat where cat.name=?1")
    List<Categories> findCategoriesByName(String name);

    @Query("select id from Categories cat where cat.name=?1")
    String findCategoryIdByName(String name);


}
