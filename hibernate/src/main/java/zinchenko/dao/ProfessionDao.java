package zinchenko.dao;

import zinchenko.domain.Car;
import zinchenko.domain.Profession;

import java.util.List;

public interface ProfessionDao {

    public List<Car> findAll();

    public Profession find(Long id);

    public Long save(Profession profession);

    public void delete(Profession profession);

}
