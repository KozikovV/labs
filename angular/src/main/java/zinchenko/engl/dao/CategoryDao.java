package zinchenko.engl.dao;

import zinchenko.engl.bean.Category;

import java.util.List;

public interface CategoryDao {

    List<Category> findAll();

    Category find(Long id);

}
