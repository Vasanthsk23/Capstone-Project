package com.capstone.project.busbooking;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String emailId;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    private String enCodedPassword;

    @NotEmpty(message = "FirstName cannot be empty.")
    @Size(min = 5,max = 300)
    private String firstName;

    private String lastName;

    @NotNull(message = "Age cannot be empty")
    private int age;

    @NotEmpty(message = "Gender cannot be empty.")
    private String gender;

    @NotEmpty(message = "Address cannot be empty.")
    private String address;

    @NotEmpty(message = "Contact details cannot be empty")
    private String contactNumber;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.emailId;
    }
}
