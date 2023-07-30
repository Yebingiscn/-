package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    int user_id;
    String user_name;
    String password;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    String telephone = "";
    String mail;
    @JsonIgnore
    int is_admin;

    @JsonIgnore
    public String getMail_code() {
        return mail_code;
    }

    @JsonProperty
    public void setMail_code(String mail_code) {
        this.mail_code = mail_code;
    }

    @TableField(exist = false)
    String mail_code;

    public User() {

    }
}
