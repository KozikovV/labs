package zinchenko.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Table;

/**
 * User: zinchenko
 * Date: 25.01.14
 */
//@Entity
@Table(name = "FLAT_HOUSE")
@DiscriminatorValue(value = "F")
public class FlatHouse extends House {

    @Column(name = "HAVE_GARAGE")
    private Boolean haveGarage;

    public Boolean getHaveGarage() {
        return haveGarage;
    }

    public void setHaveGarage(Boolean haveGarage) {
        this.haveGarage = haveGarage;
    }
}
