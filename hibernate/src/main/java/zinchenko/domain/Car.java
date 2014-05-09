package zinchenko.domain;

import javax.persistence.*;

/**
 * User: zinchenko
 * Date: 13.01.14
 */
@Entity
@Table(name = "CAR")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Car {

    @Id
    @Column(name="car_id")
    @GeneratedValue(generator = "car_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "car_gen", sequenceName = "car_seq")
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
