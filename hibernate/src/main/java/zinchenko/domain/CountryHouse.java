package zinchenko.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: zinchenko
 * Date: 25.01.14
 */
@Entity
@Table(name = "COUNTRY_HOUSE")
@DiscriminatorValue(value = "C")
public class CountryHouse extends House {

    @Column(name = "YARD_SIZE")
    private Long yardSize;

    public Long getYardSize() {
        return yardSize;
    }

    public void setYardSize(Long yardSize) {
        this.yardSize = yardSize;
    }

}
