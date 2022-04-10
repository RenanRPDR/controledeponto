package br.com.controledeponto.exceptions;

public class TempoMinimoDeAlmocoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -350318703371525636L;

	public TempoMinimoDeAlmocoException(String mensagem) {
		super(mensagem);
	}

	public TempoMinimoDeAlmocoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
