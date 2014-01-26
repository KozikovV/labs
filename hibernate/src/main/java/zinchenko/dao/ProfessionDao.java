package zinchenko.dao;

import zinchenko.domain.Car;
import zinchenko.domain.Profession;

import java.util.List;

public interface ProfessionDao {

    public List<Profession> findAllGetCurrentSes();

    public List<Profession> findAllOpenSes();

    public Profession find(Long id);

    public Long save(Profession profession);

    public void delete(Profession profession);

    public Long saveWithInterceptor(Profession profession);

}
