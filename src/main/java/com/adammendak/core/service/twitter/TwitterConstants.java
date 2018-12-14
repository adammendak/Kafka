package com.adammendak.core.service.twitter;

import lombok.Getter;

@Getter
public enum TwitterConstants {
    CONSUMER_KEY("EW0xxAL2KCluGVVdOoMBrvTYm"),
    CONSUMER_SECRET("wwaPIi3zJczIoMZ4goPOwwHMQqCnCv3coyBq7fCY0eecGDqEYj"),
    TOKEN("1072545318426824704-5NpW6j8DK60QL1TMoYavXFg8B0YD4t"),
    TOKEN_SECRET("GgZiGRHRZkziH5BTmDRYweeHlrocH8E6JsXyUAu1hMkag");

    private String value;

    TwitterConstants(String value) {
        this.value = value;
    }
}
