package com.joaogabrielferr.spring_api.data.VO.v1.security;

import com.joaogabrielferr.spring_api.model.UserRole;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class SignupVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String userName;

    private String password;

    private UserRole role;

    public SignupVO(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignupVO signupVO = (SignupVO) o;
        return Objects.equals(userName, signupVO.userName) && Objects.equals(password, signupVO.password) && role == signupVO.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password, role);
    }
}
