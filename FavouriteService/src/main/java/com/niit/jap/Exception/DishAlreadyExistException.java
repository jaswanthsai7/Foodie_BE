package com.niit.jap.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT , reason = "Dish already Exist in User Favourites")
public class DishAlreadyExistException extends Exception{
}
