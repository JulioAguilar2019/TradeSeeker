package sv.edu.catolica.project_final.Models;

import java.io.Serializable;
import java.util.Date;

public class WorkModel implements Serializable {
    private int id;
    private int worker_id;
    private int customer_id;
    private int state_id;
    private int category_id;
    private String profession;
    private String experience;
    private String schedule;
    private String level;
    private String price;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private CategoryModel category;
    private StateModel state;
    private WorkerModel worker;
    private CustomerModel customer;

    public WorkModel(int id, int worker_id, int customer_id, int state_id, int category_id, String profession, String experience, String schedule, String level, String price, String description, Date createdAt, Date updatedAt, CategoryModel category, StateModel state, WorkerModel worker, CustomerModel customer) {
        this.id = id;
        this.worker_id = worker_id;
        this.customer_id = customer_id;
        this.state_id = state_id;
        this.category_id = category_id;
        this.profession = profession;
        this.experience = experience;
        this.schedule = schedule;
        this.level = level;
        this.price = price;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.category = category;
        this.state = state;
        this.worker = worker;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(int worker_id) {
        this.worker_id = worker_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public StateModel getState() {
        return state;
    }

    public void setState(StateModel state) {
        this.state = state;
    }

    public WorkerModel getWorker() {
        return worker;
    }

    public void setWorker(WorkerModel worker) {
        this.worker = worker;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }
}
