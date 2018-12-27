package com.hill.eshop.constant;

public enum PropName {

    application("application.yml");

    private String fileName;

    PropName(String file) {
        this.fileName = file;
    }

    public String getFileName() {
        return fileName;
    }
}
