package br.com.fiap.exception;

public class SaldoInsuficienteException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public SaldoInsuficienteException() {
        super();
    }

    public SaldoInsuficienteException(String message) {
        super(message);
    }

    public SaldoInsuficienteException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaldoInsuficienteException(Throwable cause) {
        super(cause);
    }
}
