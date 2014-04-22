package zinchenko.engl.bean;

import javax.persistence.*;

@Entity
@Table(name="role")
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(generator = "role_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "role_seq", sequenceName = "role_seq")
    private Long id;

    @Column(name="name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
