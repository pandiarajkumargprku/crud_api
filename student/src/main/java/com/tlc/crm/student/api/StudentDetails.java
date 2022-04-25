package com.tlc.crm.student.api;

import com.tlc.crm.student.validator.Email;
import com.tlc.crm.student.validator.Name;

import com.tlc.validator.TlcModel;
import com.tlc.validator.type.Group.Create;
import com.tlc.validator.type.Group.Update;

/**
 * Student details
 *
 * @author PandiarajkumarG
 */
public class StudentDetails implements TlcModel {

    private Long id;

    @Email(groups = {Create.class, Update.class})
    private String email;

    @Name(groups = {Create.class, Update.class})
    private String name;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudentDetails() {
    }

    public StudentDetails(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public String toString() {
        return String.format("Id:%s \n Email:%s \n Name:%s \n\n", id, email, name);
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public Object identity() {
        return null;
    }
}
