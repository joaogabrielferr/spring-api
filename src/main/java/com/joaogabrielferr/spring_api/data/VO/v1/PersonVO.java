package com.joaogabrielferr.spring_api.data.VO.v1;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    @JsonProperty("id")
    private Long myId; //representationModel (used to implement hateoas) already has a 'id' field

    private String firstName;

    private String lastName;

    private String address;

    private String email;


    public PersonVO(){}

    public PersonVO(Long id, String firstName, String lastName, String address) {
        this.myId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public Long getMyId() {
        return myId;
    }

    public void setMyId(Long id) {
        this.myId = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PersonVO personVO = (PersonVO) o;
        return Objects.equals(myId, personVO.myId) && Objects.equals(firstName, personVO.firstName) && Objects.equals(lastName, personVO.lastName) && Objects.equals(address, personVO.address) && Objects.equals(email, personVO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), myId, firstName, lastName, address, email);
    }
}
