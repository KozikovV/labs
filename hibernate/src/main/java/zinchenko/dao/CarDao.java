package zinchenko.dao;

import zinchenko.domain.Car;

import java.util.List;

public interface CarDao {

    public List<Car> findAll();

    public Car find(Long multiId);

    public Long save(Car person);

    public void delete(Car person);

}
