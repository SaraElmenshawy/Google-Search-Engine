package com.googlesearch.app.exceptionhandler;

import java.util.Date;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.googlesearch.app.dto.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	//Handle global exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception exception,WebRequest request)
	{
		ErrorDetails errorDetails= new ErrorDetails(new Date(),"Error: Please make sure you entered the correct url and the server is up and running.",request.getDescription(false));
		return new ResponseEntity<Object>(errorDetails,null, HttpStatus.SC_INTERNAL_SERVER_ERROR);
	}
	//Handle Runtime Exception
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntimeException(RuntimeException exception,WebRequest request)
	{
		ErrorDetails errorDetails= new ErrorDetails(new Date(),"Error: Please make sure you entered the correct data.",request.getDescription(false));
		return new ResponseEntity<Object>(errorDetails,null, HttpStatus.SC_NOT_FOUND);
	}
	
}
