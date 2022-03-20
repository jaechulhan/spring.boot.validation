package net.prolancer.validation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.prolancer.validation.common.validator.CheckDateFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    @JsonIgnore
    private long id;

    @Column(name = "name")
    // username should not be null or empty
    // username should have at least 2 characters
    @NotEmpty
    @Size(min = 2, message = "{validation.username.size.error}")
    private String name;

    // email should not be null or empty
    // email should be a valid email format
    @NotEmpty
    @Email
    private String email;

    // password should not be null or empty
    // password should have at least 8 characters
    @NotEmpty
    @Size(min = 8, message = "{validation.password.size.error}")
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

    public User(String name, String email, String password, String birthday, String gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthday='" + birthday + '\'' +
                ", gender='" + gender + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
