package app.mobiledev.yoyojobsproject;

import java.util.Date;
import java.util.UUID;

public class Jobs {

    private UUID mId;
    private String mJobType;
    private int mOpenPositions;
    private Date mDateAvailable;
    private boolean mHiring;

    public Jobs(){
        mId = UUID.randomUUID();
        mDateAvailable = new Date();
    }

    public Date getmDateAvailable() {
        return mDateAvailable;
    }

    public void setmDateAvailable(Date mDateAvailable) {
        this.mDateAvailable = mDateAvailable;
    }

    public boolean ismHiring() {
        return mHiring;
    }

    public void setmHiring(boolean mHiring) {
        this.mHiring = mHiring;
    }

    public UUID getmId() {
        return mId;
    }

    public void setmId(UUID mId) {
        this.mId = mId;
    }

    public String getmJobType() {
        return mJobType;
    }

    public void setmJobType(String mJobType) {
        this.mJobType = mJobType;
    }

    public int getmOpenPositions() {
        return mOpenPositions;
    }

    public void setmOpenPositions(int mOpenPositions) {
        this.mOpenPositions = mOpenPositions;
    }
}
