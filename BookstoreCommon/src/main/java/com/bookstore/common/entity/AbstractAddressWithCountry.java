package com.bookstore.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class AbstractAddressWithCountry extends AbstractAddress {
    @ManyToOne
    @JoinColumn(name = "country_id")
    protected Country country;
}
