package com.tinoacuna.repo;

import com.tinoacuna.model.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategoryRepo extends IGenericRepo<Category, Integer> {

    //DerivedQueries
    //FROM Category c WHERE c.name = 'COMPUTERS';
    List<Category> findByName(String name);
    List<Category> findByNameLike(String name);
    List<Category> findByNameContains(String name);
    //%XYZ% -> findByNameContains
    //%XYZ  -> findByNameStartsWith
    //XYZ%  -> findByNameEndsWith
    List<Category> findByNameAndEnabled(String name, boolean enabled);
    List<Category> findByNameOrEnabled(String name, boolean enabled);
    List<Category> findByEnabled(boolean enabled);
    List<Category> findByEnabledTrue();
    List<Category> findByEnabledFalse();
    Category findOneByName(String name);
    List<Category> findTop3ByName(String name);
    List<Category> findByNameIs(String name);
    List<Category> findByNameIsNot(String name);
    List<Category> findByNameIsNull();
    List<Category> findByNameIsNotNull();
    List<Category> findByNameEquals(String name);
    List<Category> findByNameEqualsIgnoreCase(String name);
    List<Category> findByIdCategoryLessThan(Integer id);  //<
    List<Category> findByIdCategoryLessThanEqual(Integer id); //<=
    List<Category> findByIdCategoryGreaterThan(Integer id);  //>
    List<Category> findByIdCategoryGreaterThanEqual(Integer id); //>=
    List<Category> findByIdCategoryBetween(Integer id1, Integer id2);
    List<Category> findByNameOrderByDescription(String name);
    List<Category> findByNameOrderByDescriptionAsc(String name);
    List<Category> findByNameOrderByDescriptionDesc(String name);

    //JPQL Java Persistence Query Language
    @Query("FROM Category c WHERE c.name = ?1 AND c.description LIKE %?2%")
    List<Category> getByNameAndDescription1(String name, String description);

    @Query("FROM Category c WHERE c.name = :name AND c.description = :description")
    List<Category> getByNameAndDescription2(@Param("name") String x, @Param("description") String y);

    @Query("SELECT new com.tinoacuna.model.Category(c.name, c.description) FROM Category c")
    List<Category> getByNameAndDescription3();

    /////////////////////////////////////////////////////////////////

    //SQL NativeQuery
    @Query(value = "SELECT * FROM category WHERE name = :name", nativeQuery = true)
    List<Category> getByNameSQL(@Param("name") String name);

    @Modifying //para INSERT, UPDATE, DELETE
    @Query(value = "UPDATE category SET name = :name", nativeQuery = true)
    Integer updateNames(@Param("name") String name);









}
