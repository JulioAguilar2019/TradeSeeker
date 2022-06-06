package sv.edu.catolica.project_final.Models;

public class LoginResponseModel {
    private WorkerModel user;
    private String token;

    public LoginResponseModel(WorkerModel user, String token) {
        this.user = user;
        this.token = token;
    }

    public WorkerModel getUser() {
        return user;
    }

    public void setUser(WorkerModel user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
