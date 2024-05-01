package com.bookmyhotel.service.impl;

import com.bookmyhotel.dto.FavouriteDto;
import com.bookmyhotel.entity.Favourite;
import com.bookmyhotel.entity.PropertyUser;
import com.bookmyhotel.exceptions.FavouriteNotFoundException;
import com.bookmyhotel.exceptions.UnauthorizedAccessException;
import com.bookmyhotel.repository.FavouriteRepository;
import com.bookmyhotel.service.FavouriteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavouriteServiceImpl implements FavouriteService {
    @Autowired
    private FavouriteRepository favouriteRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public FavouriteDto makeFavourite(FavouriteDto favouriteDto, PropertyUser user) {
        Favourite favourite = mapToEntity(favouriteDto); //convert the dto the entity by the use of ModelMapper
        favourite.setPropertyUser(user); //set the current property user details to the entity
        Favourite saved = favouriteRepository.save(favourite); //save the favourite of the current user
        return mapToDto(saved); //returning back the dto
    }

    @Override
    public void removeFavourite(Long id, PropertyUser user) {
        Favourite favourite = favouriteRepository.findById(id).orElseThrow(() -> new FavouriteNotFoundException("Favourite Not found"));

        long userId = favourite.getPropertyUser().getId();
        long authenticateUserId = user.getId();
        //if (favourite.getPropertyUser().equals(user)){ //if the user is valid then proceed
        if (userId==authenticateUserId){
            favouriteRepository.delete(favourite); //remove the favourite
        } else {
            throw new UnauthorizedAccessException("'User is not authorized to access");
        }
    }

    public Favourite mapToEntity(FavouriteDto dto){
       return modelMapper.map(dto,Favourite.class);
    }

    public FavouriteDto mapToDto(Favourite favourite){
        return modelMapper.map(favourite,FavouriteDto.class);
    }
}
