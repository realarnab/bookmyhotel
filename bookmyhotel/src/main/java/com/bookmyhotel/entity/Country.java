package com.bookmyhotel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "id",nullable = false,unique = true)
    private long id;

    @Column(name = "country_name", nullable = false, unique = true)
    private String countryName;

}