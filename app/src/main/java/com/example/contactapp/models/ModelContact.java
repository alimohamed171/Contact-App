package com.example.contactapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class ModelContact implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String phone;

    public ModelContact( String name, String phone) {

        this.name = name;
        this.phone = phone;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
