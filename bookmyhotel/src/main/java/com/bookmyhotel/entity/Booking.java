package com.bookmyhotel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "total_nights",nullable = false)
    private Integer totalNights;

    @Column(name = "guest_name", nullable = false)
    private String guestName;

    @ManyToOne
    @JoinColumn(name = "property_user_id")
    private PropertyUser propertyUser;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;


}