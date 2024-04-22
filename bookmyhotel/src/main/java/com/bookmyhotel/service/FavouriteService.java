package com.bookmyhotel.service;

import com.bookmyhotel.dto.FavouriteDto;
import com.bookmyhotel.entity.Favourite;
import com.bookmyhotel.entity.PropertyUser;

public interface FavouriteService {
    public FavouriteDto makeFavourite(FavouriteDto favouriteDto, PropertyUser user);
    public void removeFavourite(FavouriteDto favouriteDto,PropertyUser user);
}
