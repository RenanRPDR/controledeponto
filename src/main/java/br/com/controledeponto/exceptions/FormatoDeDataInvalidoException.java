package br.com.controledeponto.exceptions;

public class FormatoDeDataInvalidoException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7589939445333967855L;

	public FormatoDeDataInvalidoException(String mensagem) {
		super(mensagem);
	}

	public FormatoDeDataInvalidoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
