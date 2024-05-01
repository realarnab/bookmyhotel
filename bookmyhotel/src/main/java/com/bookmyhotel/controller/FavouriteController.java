package com.bookmyhotel.controller;

import com.bookmyhotel.dto.FavouriteDto;
import com.bookmyhotel.entity.PropertyUser;
import com.bookmyhotel.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/favourite")
public class FavouriteController {

    @Autowired
    private FavouriteService favouriteService;

    @PostMapping
    public ResponseEntity<FavouriteDto> addFavourite(@RequestBody FavouriteDto favouriteDto, @AuthenticationPrincipal PropertyUser user){
      //directly implementing the code without service layer
        //      favourite.setPropertyUser(user);
//        Favourite savedFavourite = favouriteRepository.save(favourite);
        FavouriteDto dto = favouriteService.makeFavourite(favouriteDto, user);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> unFavourite(@PathVariable long id, @AuthenticationPrincipal PropertyUser user){
        favouriteService.removeFavourite(id,user);
        return new ResponseEntity<>("Remove favourite successfully",HttpStatus.OK);
    }

}
