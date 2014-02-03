package zinchenko.domain;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * User: zinchenko
 * Date: 19.01.14
 */
//@Entity
@Table(name="PASSENGER_CAR")
public class PassengerCar extends Car{

    @Column(name = "PASSENGERS_NUMBER")
    private Integer passengersNumber;

    public Integer getPassengersNumber() {
        return passengersNumber;
    }

    public void setPassengersNumber(Integer passengersNumber) {
        this.passengersNumber = passengersNumber;
    }
}
