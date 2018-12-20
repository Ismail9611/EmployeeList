package com.task.model;


import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {


    public Employee(){
    }

    public Employee(String fullName, Long age, String jobTitle, String homeCity, String experience) {
        this.fullName = fullName;
        this.age = age;
        this.jobTitle = jobTitle;
        this.homeCity = homeCity;
        this.experience = experience;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "age")
    private Long age;

    @Column(name = "jobTitle")
    private String jobTitle;

    @Column(name = "homeCity")
    private String homeCity;

    @Column(name = "experience")
    private String experience;

    @Column(name = "photoUrl")
    private String photoUrl;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
