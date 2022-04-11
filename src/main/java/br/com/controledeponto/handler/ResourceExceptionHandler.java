package br.com.controledeponto.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.controledeponto.batidas.exceptions.BatidaRepetidaException;
import br.com.controledeponto.batidas.exceptions.BatidasExcedidasException;
import br.com.controledeponto.batidas.exceptions.CampoNaoInformadoException;
import br.com.controledeponto.batidas.exceptions.FinalDeSemanaException;
import br.com.controledeponto.batidas.exceptions.FormatoDeDataInvalidoException;
import br.com.controledeponto.batidas.exceptions.TempoMinimoDeAlmocoException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(FormatoDeDataInvalidoException.class)
	public ResponseEntity<String> handleFormatoDeDataInvalidoException(FormatoDeDataInvalidoException e,
			HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data e hora em formato inválido");
	}
	
	@ExceptionHandler(CampoNaoInformadoException.class)
	public ResponseEntity<String> handleCampoNaoInformadoException(CampoNaoInformadoException e,
			HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campo obrigatório não informado");
	}
	
	@ExceptionHandler(BatidasExcedidasException.class)
	public ResponseEntity<String> handleBatidasExcedidasException(BatidasExcedidasException e,
			HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Apenas 4 horários podem ser registrados por dia");
	}
	
	@ExceptionHandler(TempoMinimoDeAlmocoException.class)
	public ResponseEntity<String> handleTempoMinDeAlmocoException(TempoMinimoDeAlmocoException e,
			HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Deve haver no mínimo 1 hora de almoço");
	}
	
	@ExceptionHandler(FinalDeSemanaException.class)
	public ResponseEntity<String> handleFinalDeSemanaException(FinalDeSemanaException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Sábado e domingo não são permitidos como dia de trabalho");
	}
	
	@ExceptionHandler(BatidaRepetidaException.class)
	public ResponseEntity<String> handleBatidaRepetidaException(BatidaRepetidaException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Horários já registrado");
	}
}
