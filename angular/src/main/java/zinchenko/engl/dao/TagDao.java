package zinchenko.engl.dao;

import zinchenko.engl.bean.Tag;

import java.io.Serializable;
import java.util.List;

public interface TagDao {

    List<Tag> findAll();

    Long create(Tag tag);

}
