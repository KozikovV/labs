package zinchenko.domain;

import javax.persistence.*;

/**
 * User: zinchenko
 * Date: 25.01.14
 */

@Entity
@Table(name = "OWNER_PERSON")
@PrimaryKeyJoinColumns(value = {
        @PrimaryKeyJoinColumn(name = "FIRST_NAME"),
        @PrimaryKeyJoinColumn(name = "LAST_NAME")
})
public class OwnerPerson extends Person {

    @Column(name = "STOCK")
    private Long stock;

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }
}
