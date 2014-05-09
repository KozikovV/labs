package zinchenko.dao;

import zinchenko.domain.Car;

import java.util.List;

public interface CarDao {

    public List<Car> findAll();

    public Car find(Long id);

    public List<Car> find(List<String> models);

    public List<Car> find(Integer from, Integer quantity);

    public Long save(Car car);

    public void merge(Car car);

    public void delete(Car car);

}
