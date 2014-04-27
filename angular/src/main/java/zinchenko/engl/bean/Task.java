package zinchenko.engl.bean;

//import org.codehaus.jackson.annotate.JsonBackReference;
//import org.codehaus.jackson.annotate.JsonManagedReference;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @Column(name = "task_id")
    @GeneratedValue(generator = "task_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "task_generator", sequenceName = "task_seq")
    private Long id;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

//    @JsonManagedReference
    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
    private List<TaskComment> taskComments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<TaskComment> getTaskComments() {
        return taskComments;
    }

    public void setTaskComments(List<TaskComment> taskComments) {
        this.taskComments = taskComments;
    }
}
