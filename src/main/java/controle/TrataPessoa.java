package controle;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.PersistenceException;
import modelo.Pessoa;

public class TrataPessoa {
    
    private EntityManagerFactory fabrica;
    
    public void setEntityManager(EntityManagerFactory fabrica) {
        this.fabrica = fabrica;
    }

    public EntityManager getEntityManager() {
        return fabrica.createEntityManager();
    }
    
    public void adicionar(Pessoa pessoa) {
        EntityManager entidade = null;
        try {
            entidade = getEntityManager();
            entidade.getTransaction().begin();
            entidade.persist(pessoa);
            entidade.getTransaction().commit();
            System.out.print("\n\nCadastro realizado com sucesso !\n\n");
        }
        catch (PersistenceException ex) {
            if (entidade != null)
                entidade.getTransaction().rollback();
            System.err.print("\n\n" + ex.getMessage() + "\n\n");
        }
        finally {
            if (entidade != null)
                entidade.close();
        }
    }
    
    public void alterar(Pessoa pessoa) {
        EntityManager entidade = null;
        try {
            entidade = getEntityManager();
            if (entidade.find(Pessoa.class, pessoa.getId()) == null)
                throw new PersistenceException("Pessoa não cadastrada !");
            entidade.getTransaction().begin();
            entidade.merge(pessoa);
            entidade.getTransaction().commit();
            System.out.print("\n\nAlteração realizada com sucesso !\n\n");
        }
        catch (PersistenceException ex) {
            if (entidade != null)
                entidade.getTransaction().rollback();
            System.err.print("\n\n" + ex.getMessage() + "\n\n");
        }
        finally {
            if (entidade != null)
                entidade.close();
        }
    }
    
    public void remover(Pessoa pessoa) {
        EntityManager entidade = null;
        try {
            entidade = getEntityManager();
            entidade.getTransaction().begin();
            pessoa = entidade.getReference(Pessoa.class, pessoa.getId());
            entidade.remove(pessoa);
            entidade.getTransaction().commit();
            System.out.print("\n\nExclusão realizada com sucesso !\n\n");
        }
        catch (PersistenceException ex) {
            if (entidade != null)
                entidade.getTransaction().rollback();
            System.err.print("\n\n" + ex.getMessage() + "\n\n");
        }
        finally {
            if (entidade != null)
                entidade.close();
        }
    }
    
    public Pessoa busca(Pessoa pessoa) {
        try {
            EntityManager entidade = getEntityManager();
            pessoa = entidade.find(Pessoa.class, pessoa.getId());
            if (pessoa == null)
                throw new PersistenceException("Pessoa não encontrada !");
        }
        catch (PersistenceException ex) {
            System.err.print("\n\n" + ex.getMessage() + "\n\n");
        }
        return pessoa;
    }
    
    public List<Pessoa> buscaTodos() {
        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        try {
            EntityManager entidade = getEntityManager();
            CriteriaBuilder criterio = entidade.getCriteriaBuilder();
            CriteriaQuery<Pessoa> consulta = criterio.createQuery(Pessoa.class);
            Root<Pessoa> raiz = consulta.from(Pessoa.class);
            pessoas = entidade.createQuery(consulta.select(raiz)).getResultList();
            if (pessoas.isEmpty())
                throw new PersistenceException("Não existem pessoas cadastradas !");
        }
        catch (PersistenceException ex) {
            System.err.print("\n\n" + ex.getMessage() + "\n\n");
        }
        return pessoas;
    }
    
}
