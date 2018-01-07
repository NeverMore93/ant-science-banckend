package com.antScience.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    private String id;
    @Column(name = "user_name")
    private String userName;

    @JsonIgnore
    private String password;
    private String phone;
    private String email;
    private String status;
    private String avatar;
    private Date creationTime;
    private Date updatedTime;

}
