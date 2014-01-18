package zinchenko.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PERSON")
public class Person {

    @EmbeddedId
    private MultiId id;

    @Column(name = "ABOUT_ME")
    private String aboutMe;

    @ManyToOne
    @JoinColumn(name = "PROFESSION_ID")
    private Profession profession;

//    @Column(name="BIRTHDATE")
//    private Date birthdate;

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
}
