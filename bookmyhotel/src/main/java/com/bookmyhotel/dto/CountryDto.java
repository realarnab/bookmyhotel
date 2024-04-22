package com.bookmyhotel.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
@Setter
@NoArgsConstructor
public class CountryDto {

    @Positive(message = "country id should be greater than zero") //ensure that value of id is greater than zero
    private long id;
    @NotNull
    @Size(min = 2,message = "country name should be more than 2 characters")
    //@UniqueElements(message = "country name should be unique")
    private String countryName;
}
