package com.example.konrad.notatnik.ui.main.storage;

public class StorageTask {
    private String mTitle = "Bez nazwy";
    private String mDescription;
    private String mLastModificationDate;

    public StorageTask(String mTitle, String mDescription, String mLastModificationDate){
        this.mDescription = mDescription;
        this.mTitle = mTitle;
        this.mLastModificationDate = mLastModificationDate;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmLastModificationDate() {
        return mLastModificationDate;
    }

    public void setmLastModificationDate(String mLastModificationDate) {
        this.mLastModificationDate = mLastModificationDate;
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
