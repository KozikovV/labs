package zinchenko.engl.bean;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {

    //    @Id
    //    @Column(name = "image_id")
    //    @GeneratedValue(generator = "image_gen", strategy = GenerationType.SEQUENCE)
    //    @SequenceGenerator(name = "image_gen", sequenceName = "image_seq")
    @Id
    @Column(name = "image_id")
    @GeneratedValue(generator = "img_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "img_generator", sequenceName = "img_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "file_name")
    private String fileName;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

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
