package zinchenko.engl.bean;

import javax.persistence.*;

@Entity
@Table(name="image")
public class Image {

    @Id
    @Column(name = "image_id")
    @GeneratedValue(generator = "image_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "image_gen", sequenceName = "image_seq")
    private Long id;

    @Column(name = "name")
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
