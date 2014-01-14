package zinchenko.domain;

import javax.persistence.*;

@Entity
@Table(name = "PERSON")
public class Person {

    @EmbeddedId
    private MultiId id;

    @Column(name = "ABOUT_ME")
    private String aboutMe;

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
}
