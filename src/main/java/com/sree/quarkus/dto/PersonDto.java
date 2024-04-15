package com.sree.quarkus.dto;

import java.util.Objects;

/**
 * This dto class is used for send object while save or update the person's info.
 */
public class PersonDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String city;

    public PersonDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PersonDto personDto = (PersonDto) object;
        return Objects.equals(id, personDto.id) && Objects.equals(name, personDto.name) && Objects.equals(email, personDto.email) && Objects.equals(phoneNumber, personDto.phoneNumber) && Objects.equals(city, personDto.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phoneNumber, city);
    }
}
