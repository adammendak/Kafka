package com.adammendak.core.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private String created_at;

    @Column(name = "text")
    @NotNull
    private String text;

    public Tweet(String created_at, @NotNull String text) {
        this.created_at = created_at;
        this.text = text;
    }
}
