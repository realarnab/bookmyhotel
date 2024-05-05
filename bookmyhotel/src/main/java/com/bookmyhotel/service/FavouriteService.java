package com.bookmyhotel.service;

import com.bookmyhotel.dto.FavouriteDto;
import com.bookmyhotel.entity.Favourite;
import com.bookmyhotel.entity.PropertyUser;
import org.apache.catalina.LifecycleState;

import java.util.List;

public interface FavouriteService {
    public FavouriteDto makeFavourite(FavouriteDto favouriteDto, PropertyUser user);
    public void removeFavourite(Long id,PropertyUser user);

    List<FavouriteDto> getAllFavouriteOfUser(PropertyUser user);
}
