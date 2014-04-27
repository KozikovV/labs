package zinchenko.engl.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//import org.codehaus.jackson.annotate.JsonBackReference;
//import org.codehaus.jackson.annotate.JsonIgnore;
//import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Category {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(generator = "category_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "category_seq", sequenceName = "category_seq")
    private Long id;

    @JsonManagedReference
    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.EAGER)
    private List<Category> categories;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

//    @JsonIgnore
//    @JsonManagedReference("category-test")
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Test> tests;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

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

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
