package softuni.workshop.web.models;

public class UserRegisterModel {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;

    public UserRegisterModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
