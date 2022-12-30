package com.compras.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ResourceExceptionHandler {
	
	//SERVE PARA TRATAR O ERRO DE NÃO ENCONTRAR NEM UM USUÁRIO
	
		@ExceptionHandler(ObjectNotFoundException.class)
		public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){

			StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err) ;
		}
		

		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){

			ValidationError err = new ValidationError(HttpStatus.NOT_FOUND.value(), "Erro de validação", System.currentTimeMillis());
			for(FieldError x : e.getBindingResult().getFieldErrors()) {
				err.addError(x.getField(), x.getDefaultMessage());
			}
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err) ;
		}
		
		@ExceptionHandler(Exception.class)
		public ResponseEntity<StandardError> badRquest(Exception e, HttpServletRequest request){

			StandardError er = new StandardError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Requisição invalida, verifique os parametros", System.currentTimeMillis());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er) ;
		}
}
