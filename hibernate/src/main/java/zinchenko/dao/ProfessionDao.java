package zinchenko.dao;

import zinchenko.domain.Profession;

import java.util.List;

public interface ProfessionDao {

    public List<Profession> findAllGetCurrentSes();

    public List<Profession> findAllOpenSes();

    public Profession find(Long id);

    public Long save(Profession profession);

    public void saveBatchNaive(List<Profession> professions);

    public void saveBatch(List<Profession> professions);

    public void delete(Profession profession);

    public Long saveWithInterceptor(Profession profession);

    public Profession load(Long id);

    public void growUpAllLevels();

}
