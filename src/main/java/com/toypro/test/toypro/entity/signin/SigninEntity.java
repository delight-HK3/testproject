package com.toypro.test.toypro.entity.signin;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity(name="t_toy_user")
public class SigninEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NO")
    private int no;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USER_PWD")
    private String userPwd;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_EMAIL")
    private String userEmail;

    @Column(name = "PHONE_NO")
    private String phoneNo;

    @Column(name = "USER_TYPE")
    private String usertype;

    @Column(name = "REG_DATE")
    private Date regdate;
}
