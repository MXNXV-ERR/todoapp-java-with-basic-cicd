package com.mxnxv.todoapp.model;

import java.util.Date;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@EnableAutoConfiguration
@Entity
@Table(name="todo")
@SequenceGenerator(name = "todo_seq", sequenceName = "todo_seq", allocationSize = 1)
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable=false)
    private String title;
    @Column(nullable=false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @Column(nullable=false)
    private String status;

    public ToDo() {
     
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ToDo [id=" + id + ", title=" + title + ", date=" + date + ", status=" + status + "]";
    }
    
}
