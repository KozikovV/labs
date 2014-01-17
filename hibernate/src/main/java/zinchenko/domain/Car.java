package zinchenko.domain;

import javax.persistence.*;

/**
 * User: zinchenko
 * Date: 13.01.14
 */
@Entity(name = "CAR")
public class Car {

    @Id
    private Long id;

    @MapsId("multiId")
    @JoinColumns({
            @JoinColumn(name = "person_first_name", referencedColumnName = "first_name"),
            @JoinColumn(name = "person_last_name", referencedColumnName = "last_name")
    })
    @OneToOne
    private Person person;

    private String model;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
