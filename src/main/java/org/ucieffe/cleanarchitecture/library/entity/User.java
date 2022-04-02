package org.ucieffe.cleanarchitecture.library.entity;

import java.time.LocalDate;

public class User {
    private String identifier;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Boolean isSuspended;

    public User(String identifier, String firstName, String lastName, LocalDate birthDate, Boolean isSuspended) {
        this.identifier = identifier;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.isSuspended = isSuspended;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Boolean getSuspended() {
        return isSuspended;
    }
}
