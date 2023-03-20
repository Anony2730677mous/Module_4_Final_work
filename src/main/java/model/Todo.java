package model;

import jakarta.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.Type;

import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(schema = "todo_list", name = "todos")
public class Todo {
    private static final Logger LOGGER = LogManager.getLogger(Todo.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @Column(name = "description", length = 255)
    private String description;
    @Column(name = "is_done", columnDefinition = "BIT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isDone;
    @Column(name = "target_date")
    private Date targetdate;
    @Column(name = "username", length = 255)
    private String username;
    @Column(name = "title", length = 255)
    private String title;

    public Todo() {
    }

    public Todo(String title, String userName, String description, Date targetdate, Boolean isDone) {

        this.title = title;
        this.username = userName;
        this.description = description;
        this.targetdate = targetdate;
        this.isDone = isDone;
        LOGGER.info("The task with title: " + title + " was created by a user with a username: " + userName);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public Date getTargetdate() {
        return targetdate;
    }

    public void setTargetdate(Date targetdate) {
        this.targetdate = targetdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Todo{" +
                " description='" + description + '\'' +
                ", isDone=" + isDone +
                ", targetdate=" + targetdate +
                ", userName='" + username + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
