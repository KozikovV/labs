package zinchenko.transaction.oneOrchestrated.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderBean implements Serializable{

    private Long price;

    private String name;

    private String firstName;

    private String lastName;

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
