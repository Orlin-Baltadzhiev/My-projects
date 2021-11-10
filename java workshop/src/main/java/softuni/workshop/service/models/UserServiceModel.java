package softuni.workshop.service.models;

import java.util.Set;

public class UserServiceModel {
    private String username;
    private String password;
    private String email;
    private Set<RoleServiceModel> authorities;

    public UserServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<RoleServiceModel> getAuthorities() {
        return authorities;
    }

    public UserServiceModel setAuthorities(Set<RoleServiceModel> authorities) {
        this.authorities = authorities;
        return this;
    }
}
