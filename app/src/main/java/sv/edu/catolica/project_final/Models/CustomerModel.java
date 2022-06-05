package sv.edu.catolica.project_final.Models;

import java.util.Date;

public class CustomerModel {
    private int id;
    private String name;
    private String email;
    private Date emailVerifiedAt;
    private String profession;
    private Date birthday;
    private String gender;
    private String stateID;
    private String phone;
    private String type;
    private String device;
    private Date createdAt;
    private Date updatedAt;

    public CustomerModel(int id, String name, String email, Date emailVerifiedAt, String profession, Date birthday, String gender, String stateID, String phone, String type, String device, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.emailVerifiedAt = emailVerifiedAt;
        this.profession = profession;
        this.birthday = birthday;
        this.gender = gender;
        this.stateID = stateID;
        this.phone = phone;
        this.type = type;
        this.device = device;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(Date emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStateID() {
        return stateID;
    }

    public void setStateID(String stateID) {
        this.stateID = stateID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
