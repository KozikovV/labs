package zinchenko.domain;

import javax.persistence.*;

/**
 * User: zinchenko
 * Date: 25.01.14
 */
@Entity
@Table(name = "HOUSE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISCRIMINATOR",
        discriminatorType = DiscriminatorType.STRING)
public class House {

    @Id
    @GeneratedValue
    @Column(name = "HOUSE_ID")
    private Long id;

    @Column(name = "LOCATION")
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
