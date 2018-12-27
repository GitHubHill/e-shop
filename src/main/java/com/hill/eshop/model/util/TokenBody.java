package com.hill.eshop.model.util;

public class TokenBody {

    public TokenBody() {

    }

    private int id;

    private int roleID;

    private int userID;

    private String account;

    private int topAgentID;

    private long loginTime;

    private long exp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getTopAgentID() {
        return topAgentID;
    }

    public void setTopAgentID(int topAgentID) {
        this.topAgentID = topAgentID;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

}
