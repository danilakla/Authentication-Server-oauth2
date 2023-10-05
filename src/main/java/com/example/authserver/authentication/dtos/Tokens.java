package com.example.authserver.authentication.dtos;

public class Tokens {
    private String ref;
    private String acc;

    public Tokens(String ref, String acc) {
        this.acc=acc;
        this.ref=ref;
    }

    public Tokens() {

    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }
}

