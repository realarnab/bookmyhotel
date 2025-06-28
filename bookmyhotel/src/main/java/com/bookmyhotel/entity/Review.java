package com.bookmyhotel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "content", length = 1000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;         //perform the many-to-one mapping

    @ManyToOne
    @JoinColumn(name = "property_user_id")
    private PropertyUser propertyUser;

}