package zinchenko.dao;

import zinchenko.domain.Car;

import java.util.List;

public interface CarDao {

    public List<Car> findAll();

    public Car find(Long id);

    public Long save(Car car);

    public void delete(Car car);

}
