package com.hill.eshop.model.util;

public class TokenInfo {

    public TokenInfo() {
    }

    public TokenInfo(TokenBody tokenBody) {
        this.tokenBody = tokenBody;
    }

    public TokenInfo(TokenBody tokenBody, String newToken) {
        this.tokenBody = tokenBody;
        this.newToken = newToken;
    }

    private TokenBody tokenBody;

    private String newToken;

    public TokenBody getTokenBody() {
        return tokenBody;
    }

    public void setTokenBody(TokenBody tokenBody) {
        this.tokenBody = tokenBody;
    }

    public String getNewToken() {
        return newToken;
    }

    public void setNewToken(String newToken) {
        this.newToken = newToken;
    }

}
