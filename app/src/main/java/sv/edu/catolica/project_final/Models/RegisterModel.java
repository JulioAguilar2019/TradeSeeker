package sv.edu.catolica.project_final.Models;

public class RegisterModel {
    private String name;
    private String email;
    private String password;
    private String password_confirmed;
    private String profession;
    private String birthday;
    private String gender;
    private int state_id;
    private String phone;

    public RegisterModel(String name, String email, String password, String password_confirmed, String profession, String birthday, String gender, int state_id, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.password_confirmed = password_confirmed;
        this.profession = profession;
        this.birthday = birthday;
        this.gender = gender;
        this.state_id = state_id;
        this.phone = phone;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirmed() {
        return password_confirmed;
    }

    public void setPassword_confirmed(String password_confirmed) {
        this.password_confirmed = password_confirmed;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getState_id() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
