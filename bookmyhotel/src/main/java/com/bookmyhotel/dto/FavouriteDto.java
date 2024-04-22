package com.bookmyhotel.dto;

import com.bookmyhotel.entity.Property;
import com.bookmyhotel.entity.PropertyUser;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FavouriteDto {
    private Boolean isFavourite = false;

    private PropertyUser propertyUser;

    private Property property;
}
