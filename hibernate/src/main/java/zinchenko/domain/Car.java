package zinchenko.domain;

import org.hibernate.annotations.Generated;

import javax.persistence.*;

/**
 * User: zinchenko
 * Date: 13.01.14
 */
@Entity
@Table(name = "CAR")
public class Car {

    @Id
    @GeneratedValue
    private Long id;

//    @MapsId("multiId")
//    @JoinColumns({
//            @JoinColumn(name = "owner_first_name", referencedColumnName = "first_name"),
//            @JoinColumn(name = "owner_last_name", referencedColumnName = "last_name")
//    })
//    @OneToOne
//    private Person owner;

    private String model;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Person getOwher() {
//        return owner;
//    }
//
//    public void setOwner(Person owner) {
//        this.owner = owner;
//    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
