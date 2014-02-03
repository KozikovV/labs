package zinchenko.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

//@Entity
@Table(name = "PERSON")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    @EmbeddedId
    private MultiId id;

    @Column(name = "ABOUT_ME")
    private String aboutMe;

    @ManyToOne
    @JoinColumn(name = "PROFESSION_ID")
    private Profession profession;

    @Column(name="BIRTHDATE")
    private Date birthdate;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "spouse_first_name"),
            @JoinColumn(name = "spouse_last_name")
    })
    private Person spouse;

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public MultiId getId() {
        return id;
    }

    public void setId(MultiId id) {
        this.id = id;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }
}
