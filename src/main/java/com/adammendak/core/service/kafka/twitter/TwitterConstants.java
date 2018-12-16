package com.adammendak.core.service.kafka.twitter;

import lombok.Getter;

@Getter
public enum TwitterConstants {
    CONSUMER_KEY("T7VJP5tf4tLhywTQWuhbMSirb"),
    CONSUMER_SECRET("ra7bm2fAOEDaGlOzEe65F3VY3G1DhkFKgS4T5k2aBPOX0z9lBq"),
    TOKEN("1072545318426824704-4aedNg6eW5TVY6JI5EPECeoYayObow"),
    TOKEN_SECRET("hbsuEGE2xpubrnHF9aLlbJUIeG016Q9JRmWUMaMx6tNRh");

    private String value;

    TwitterConstants(String value) {
        this.value = value;
    }
}
