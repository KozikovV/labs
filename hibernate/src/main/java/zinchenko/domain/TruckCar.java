package zinchenko.domain;

import javax.persistence.*;

/**
 * User: zinchenko
 * Date: 19.01.14
 */
//@Entity
@Table(name="TRUCK_CAR")
public class TruckCar extends Car{

    @Column(name="BODY_SIZE")
    private Integer bodySize;

    public Integer getBodySize() {
        return bodySize;
    }

    public void setBodySize(Integer bodySize) {
        this.bodySize = bodySize;
    }
}
