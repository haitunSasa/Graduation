package com.graduation.bean;

import java.sql.Timestamp;

/**
 * Created by liyan on 2017/4/16.
 */

public class Authentication {
    private int authenticateId;
    private Timestamp authenticateTime;
    private Integer userId;
    private Integer typeId;

    public int getAuthenticateId() {
        return authenticateId;
    }

    public void setAuthenticateId(int authenticateId) {
        this.authenticateId = authenticateId;
    }

    public Timestamp getAuthenticateTime() {
        return authenticateTime;
    }

    public void setAuthenticateTime(Timestamp authenticateTime) {
        this.authenticateTime = authenticateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Authentication that = (Authentication) o;

        if (authenticateId != that.authenticateId) return false;
        if (authenticateTime != null ? !authenticateTime.equals(that.authenticateTime) : that.authenticateTime != null)
            return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (typeId != null ? !typeId.equals(that.typeId) : that.typeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = authenticateId;
        result = 31 * result + (authenticateTime != null ? authenticateTime.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (typeId != null ? typeId.hashCode() : 0);
        return result;
    }
}
