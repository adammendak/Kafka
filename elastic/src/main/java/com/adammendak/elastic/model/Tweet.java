package com.adammendak.elastic.model;

import javax.validation.constraints.NotNull;

public class Tweet {

    private String created_at;
    private String text;

    public Tweet(String created_at, @NotNull String text) {
        this.created_at = created_at;
        this.text = text;
    }
}
