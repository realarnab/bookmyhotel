package com.bookmyhotel.dto;

import com.bookmyhotel.entity.Country;
import com.bookmyhotel.entity.Location;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDto {
    @NotEmpty(message = "this field should not be empty")
    @Positive(message = "id should be greater than zero")
    private long id;
    @NotNull(message = "Needs a name for the property")
    private String propertyName;
    @Positive(message = "bedrooms should be greater than zero")
    private int bedrooms;
    @Positive(message = "bathrooms should be greater than zero")
    private int bathrooms;
    @Positive(message = "guest no. should be grater than zero")
    private int guests;
    @Positive(message = "price should be greater than zero")
    private int nightlyPrice;
    private Country country;
    private Location location;
}
