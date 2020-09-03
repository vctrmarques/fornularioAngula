package br.com.cast.avaliacao.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.cast.avaliacao.generico.ApiResponse;

public class Utils {
	
	public static ResponseEntity<?> created(Boolean sucess, String message) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(sucess, message));
	}
	
	public static ResponseEntity<?> badRequest(Boolean sucess, String message) {
		return ResponseEntity.badRequest().body(new ApiResponse(sucess, message));
	}
}
