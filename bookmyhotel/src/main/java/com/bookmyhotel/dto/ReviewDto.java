package com.bookmyhotel.dto;

import com.bookmyhotel.entity.Property;
import com.bookmyhotel.entity.PropertyUser;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDto {
    private String content;

    private Property property;

    private PropertyUser propertyUser;
}
