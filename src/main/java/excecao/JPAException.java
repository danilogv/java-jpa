package excecao;

public class JPAException extends RuntimeException {
    
    private String mensagem;
    
    public JPAException(String mensagem) {
        super(mensagem);
    }
    
}
