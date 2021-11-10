package softuni.workshop.service.models;

public class RoleServiceModel {
    private String authority;

    public RoleServiceModel() {
    }

    public String getAuthority() {
        return authority;
    }

    public RoleServiceModel setAuthority(String authority) {
        this.authority = authority;
        return this;
    }
}
