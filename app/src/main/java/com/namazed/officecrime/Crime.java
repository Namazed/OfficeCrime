package com.namazed.officecrime;


import java.util.UUID;

public class Crime {
    private String mTitle;
    private UUID mId;

    public Crime() {
        //Generating unique ID
        mId = UUID.randomUUID();

    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public UUID getId() {
        return mId;
    }
}
