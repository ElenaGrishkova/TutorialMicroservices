package ru.diasoft.tutorial.microservices.module5.entity;

import javax.persistence.*;

@Entity
public class Message {

    private Long id;
    private String text;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(name = "text", nullable = false)
    public String getText() {
        return text;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

}
