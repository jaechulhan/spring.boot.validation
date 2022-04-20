package net.prolancer.validation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.prolancer.validation.common.validator.CheckDateFormat;

import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

public class User {
    @JsonIgnore
    private String userId;

    // username should not be null or empty
    // username should have at least 2 characters
    @NotNull
    @NotEmpty
    @Size(min = 2, max= 255,  message = "{validation.username.size.error}")
    private String userName;

    // email should not be null or empty
    // email should be a valid email format
    @NotNull
    @NotEmpty
    @Email
    private String email;

    // password should not be null or empty
    // password should have at least 8 characters
    @NotEmpty
    @Size(min = 8, max= 255, message = "{validation.password.size.error}")
    private String password;

    // birthday should not be null or empty
    // birthday should be MM/dd/yyyy
    @NotEmpty
    @CheckDateFormat(pattern = "MM/dd/yyyy", message = "{validation.birthday.dateformat.error}")
    private String birthday;

    // gender must match "Male or Female"
    @Pattern(regexp = "Male|Female", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String gender;

    // address should have at least one
    // address needs to validate
    @Transient // If it's related to database, need to use "@OneToMany"
    @NotEmpty
    @Valid
    private List<Address> addresses;

    public User() {
    }

    public User(String userName, String email, String password, String birthday, String gender) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addressList) {
        this.addresses = addressList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthday='" + birthday + '\'' +
                ", gender='" + gender + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
