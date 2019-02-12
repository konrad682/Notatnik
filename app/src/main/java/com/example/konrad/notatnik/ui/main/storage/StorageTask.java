package com.example.konrad.notatnik.ui.main.storage;

public class StorageTask {
    private String mTitle = "Bez nazwy";
    private String mDescription= "Brak opisu";
    private int mOwn_ID;

    public StorageTask(String mTitle, String mDescription,int max_ID){
        this.mDescription = mDescription;
        this.mTitle = mTitle;
        this.mOwn_ID = max_ID;
    }

    public String getmTitle() {
        return mTitle;
    }

   public int getmOwn_ID() {
        return mOwn_ID;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
