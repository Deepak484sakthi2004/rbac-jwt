

package app.deepak.rbac_jwt.request;

import app.deepak.rbac_jwt.model.User;

import java.util.List;

public class CreateUserRequest {
    private String name;
    private String email;
    private String password;
    private List<User.Role> roles;

    public CreateUserRequest() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<User.Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<User.Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
