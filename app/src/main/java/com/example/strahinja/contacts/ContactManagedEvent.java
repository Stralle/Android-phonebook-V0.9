package com.example.strahinja.contacts;


public class ContactManagedEvent {

    public static final int NOTIFY_DATA_SET_CHANGED = 0;
    public static final int NOTIFY_ITEM_CHANGED = 1;

    private int mEventType;
    private int mAdapterPosition;

    public ContactManagedEvent(){
        mEventType = -1;
    }

    public ContactManagedEvent(int mEventType) {
        this.mEventType = mEventType;
    }

    public ContactManagedEvent(int mEventType, int mAdapterPosition) {
        this.mEventType = mEventType;
        this.mAdapterPosition = mAdapterPosition;
    }

    public int getAdapterPosition() {
        return mAdapterPosition;
    }

    public void setAdapterPosition(int mId) {
        this.mAdapterPosition = mId;
    }

    public int getEventType() {
        return mEventType;
    }

    public void setEventType(int mEventType) {
        this.mEventType = mEventType;
    }
}