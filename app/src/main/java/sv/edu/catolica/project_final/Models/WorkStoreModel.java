package sv.edu.catolica.project_final.Models;

public class WorkStoreModel {
    private int state_id;
    private int category_id;
    private String profession;
    private String experience;
    private String schedule;
    private String price;
    private String description;

    public WorkStoreModel(int state_id, int category_id, String profession, String experience, String schedule, String price, String description) {
        this.state_id = state_id;
        this.category_id = category_id;
        this.profession = profession;
        this.experience = experience;
        this.schedule = schedule;
        this.price = price;
        this.description = description;
    }

    public int getState_id() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
