 package com.example.myapplication.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseVk {

    @SerializedName("response")
    @Expose
    private List<VkUser> response = null;

    public List<VkUser> getResponse() {
        return response;
    }

    public void setResponse(List<VkUser> response) {
        this.response = response;
    }
    @Entity
    public static class VkUser {

        @SerializedName("id")
        @Expose
        @PrimaryKey
        private Integer id;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("is_closed")
        @Expose
        private Boolean isClosed;
        @SerializedName("can_access_closed")
        @Expose
        private Boolean canAccessClosed;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public Boolean getIsClosed() {
            return isClosed;
        }

        public void setIsClosed(Boolean isClosed) {
            this.isClosed = isClosed;
        }

        public Boolean getCanAccessClosed() {
            return canAccessClosed;
        }

        public void setCanAccessClosed(Boolean canAccessClosed) {
            this.canAccessClosed = canAccessClosed;
        }

    }
}
