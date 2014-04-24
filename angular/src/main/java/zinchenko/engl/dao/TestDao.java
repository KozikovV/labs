package zinchenko.engl.dao;

import zinchenko.engl.bean.Test;

public interface TestDao {

    Test find(Long id);

    Test save(Test test);

}
