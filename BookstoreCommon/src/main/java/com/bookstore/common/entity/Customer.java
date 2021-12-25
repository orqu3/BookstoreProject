package com.bookstore.common.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer extends AbstractAddressWithCountry {

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    private String fullName;

    @Column(name = "verification_code", length = 64)
    private String verificationCode;

    private Boolean enabled;

    @Column(name = "created_time")
    private Date createdTime;


    @Enumerated(EnumType.STRING)
    @Column(name = "authentication_type", length = 10)
    private AuthenticationType authenticationType;

    @Column(name = "reset_password_token", length = 30)
    private String resetPasswordToken;

    public Customer(Integer id) {
        this.id = id;
    }

    @Transient
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
