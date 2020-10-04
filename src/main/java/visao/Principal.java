package visao;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Pessoa;
import controle.TrataPessoa;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Pessoa pessoa = new Pessoa();
        TrataPessoa pessoaBd = new TrataPessoa();
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("PU");
        pessoaBd.setEntityManager(fabrica);
        Integer opcao,id;
        String nome,email;
        do {
            System.out.print("Digite a opção desejada :\n\n");
            System.out.print("1-Inserir\n2-Alterar\n3-Remover\n4-Consultar\n5-Listar\n6-Sair\n\n");
            opcao = entrada.nextInt();
            switch(opcao) {
                case 1:
                    System.out.print("Nome : ");
                    entrada.nextLine();
                    nome = entrada.nextLine();
                    System.out.print("E-mail : ");
                    email = entrada.next();
                    pessoa.setNome(nome);
                    pessoa.setEmail(email);
                    pessoaBd.adicionar(pessoa);
                    break;
                case 2:
                    System.out.print("Código da pessoa : ");
                    id = entrada.nextInt();
                    pessoa.setId(id);
                    System.out.print("Nome : ");
                    entrada.nextLine();
                    nome = entrada.nextLine();
                    System.out.print("E-mail : ");
                    email = entrada.next();
                    pessoa.setNome(nome);
                    pessoa.setEmail(email);
                    pessoaBd.alterar(pessoa);
                    break;
                case 3:
                    System.out.print("Código da pessoa : ");
                    id = entrada.nextInt();
                    pessoa.setId(id);
                    pessoaBd.remover(pessoa);
                    break;
                case 4:
                    System.out.print("Código : ");
                    id = entrada.nextInt();
                    pessoa.setId(id);
                    Pessoa pessoaAux = pessoaBd.busca(pessoa);
                    if (pessoaAux != null) {
                        System.out.print("Nome : " + pessoaAux.getNome() + "\n");
                        System.out.print("E-mail : " + pessoaAux.getEmail() + "\n\n");
                    }
                    break;
                case 5:
                    List<Pessoa> pessoas = pessoaBd.buscaTodos();
                    for (int i = 0; i < pessoas.size(); i++) {
                        System.out.print("Código : " + pessoas.get(i).getId() + "\n");
                        System.out.print("Nome : " + pessoas.get(i).getNome() + "\n");
                        System.out.print("E-mail : " + pessoas.get(i).getEmail() + "\n\n");
                    }
                    break;
                case 6:
                    break;
                default:
                    System.err.print("Opção inválida !\n\n");
            }
        } while (opcao != 6);
        entrada.close();
    }
    
}
