package com.sanjay.rxjava_operators;

public class Student {

    String name;
    String email;
    int age;
    String registrationDate;

    public Student(String name, String email, int age, String registrationDate) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.registrationDate = registrationDate;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
}
