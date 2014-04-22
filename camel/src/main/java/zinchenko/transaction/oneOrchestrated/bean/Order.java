package zinchenko.transaction.oneOrchestrated.bean;

import javax.persistence.*;

@Entity
@Table(name="order_table")
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(generator = "order_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "order_gen", sequenceName = "order_gen")
    private Long id;

    @Column(name = "price")
    private Long price;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
