package zinchenko.engl.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//import org.codehaus.jackson.annotate.JsonBackReference;
//import org.codehaus.jackson.annotate.JsonManagedReference;
//import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "test")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Test {

    @Id
    @Column(name = "test_id")
    @GeneratedValue(generator = "test_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "test_seq", sequenceName = "test_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @JsonManagedReference
    @OneToMany(mappedBy = "test", fetch = FetchType.EAGER)
    @Cascade(CascadeType.SAVE_UPDATE)
    private List<Task> tasks;

//    @JsonProperty
//    @JsonBackReference("category-test")
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
