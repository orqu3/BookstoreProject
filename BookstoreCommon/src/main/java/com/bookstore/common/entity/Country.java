package com.bookstore.common.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Table(name = "countries")
public class Country extends IdBasedEntity {

    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = false, length = 45)
    private String code;

    @OneToMany(mappedBy = "country")
    private Set<State> states;


    public Country(Integer id) {
        this.id = id;
    }

    public Country(String name) {
        this.name = name;
    }


    public Country(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Country(Integer id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
