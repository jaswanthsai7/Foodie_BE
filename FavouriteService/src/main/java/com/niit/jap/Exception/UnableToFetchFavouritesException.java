package com.niit.jap.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND , reason = "Unable to fetch favourites Exception")
public class UnableToFetchFavouritesException extends Exception {
}
