package com.niit.jap.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT , reason = "Restaurant already Exist in User Favourites")
public class RestaurantAlreadyExistException extends Exception{
}
