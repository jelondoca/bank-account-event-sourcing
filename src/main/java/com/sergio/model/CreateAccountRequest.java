package com.sergio.model;

public class CreateAccountRequest {

    private String ownerName;
    private String ownerSurnames;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerSurnames() {
        return ownerSurnames;
    }

    public void setOwnerSurnames(String ownerSurnames) {
        this.ownerSurnames = ownerSurnames;
    }
}
