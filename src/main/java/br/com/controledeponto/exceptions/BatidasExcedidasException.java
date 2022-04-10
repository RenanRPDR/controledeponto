package br.com.controledeponto.exceptions;

public class BatidasExcedidasException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6805686960428294903L;
	
	public BatidasExcedidasException(String mensagem) {
		super(mensagem);
	}

	public BatidasExcedidasException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
