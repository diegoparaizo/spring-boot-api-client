package com.paraizo.springbootapiclient.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.paraizo.springbootapiclient.exception.RecordNotFoundException;

@ControllerAdvice
public class ControlAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		return "Campo: " + ex.getBindingResult().getFieldError().getField() + " - Descrição erro: "
				+ ex.getBindingResult().getFieldError().getDefaultMessage();
	}

	@ExceptionHandler(RecordNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public String handleMethodRecordNotFoundException(RecordNotFoundException ex) {
		return "Campo: " + ex.getMessage();
	}
}
