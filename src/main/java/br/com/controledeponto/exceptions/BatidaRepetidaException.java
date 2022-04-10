package br.com.controledeponto.exceptions;

public class BatidaRepetidaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4763773862016110491L;

	public BatidaRepetidaException(String mensagem) {
		super(mensagem);
	}

	public BatidaRepetidaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
