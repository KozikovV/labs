package zinchenko.engl.bean;


import org.codehaus.jackson.annotate.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "task_comment")
public class TaskComment {

    @Id
    @Column(name = "task_comment_id")
    @GeneratedValue(generator = "task_comment_generator")
    @SequenceGenerator(name = "task_comment_generator", sequenceName = "task_comment_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reporter_id", nullable = false)
    private User reporter;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Column(name = "content")
    private String content;

    @Column(name = "created")
    private Date created;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }





}
