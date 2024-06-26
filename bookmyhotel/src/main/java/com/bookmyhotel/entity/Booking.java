package com.bookmyhotel.entity;

import com.sun.source.doctree.SeeTree;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "check_in_date", nullable = false)
    private LocalDate checkInDate;

    @Column(name = "check_out_date", nullable = false)
    private LocalDate checkOutDate;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "total_nights",nullable = false)
    private Integer totalNights;

    @Column(name = "guest_name", nullable = false)
    private String guestName;

    @Column(name = "guest_email",nullable = false)
    private String guestEmail;

    @Column(name = "guest_mobile",nullable = false)
    private String guestMobile;

    @ManyToOne
    @JoinColumn(name = "property_user_id")
    private PropertyUser propertyUser;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;


}