package br.com.cast.avaliacao.exeception;

public class CursoException extends Exception{

	private static final long serialVersionUID = 7495842281268749602L;

	private String mensagem;
	private Exception exception;
	
	public CursoException(String mensagem, Exception exception) {
		this.mensagem = mensagem;
		if (exception == null) {
			this.exception = new Exception();
		}else {
			this.exception = exception;	
		}		
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
	
}
