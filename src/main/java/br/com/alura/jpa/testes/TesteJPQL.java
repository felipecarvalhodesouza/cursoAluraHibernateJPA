package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.alura.jpa.modelo.Conta;
import br.com.alura.jpa.modelo.Movimentacao;

public class TesteJPQL {

	public static void main(String[] args) {
		
		/**
		 * Neste cenário em que usamos JPA em nosso projeto, queremos nos afastar ao máximo do mundo relacional
		 * Quando criarmos objetos no mundo Java, os atribuiremos a variáveis para podermos manipulá-los.
		 * Então chamaremos a Movimentacao de m no lugar de *, para dizer que queremos buscar estas movimentações.
		 */
		
		/**
		 * Quando criarmos objetos no mundo Java, os atribuiremos a variáveis para podermos manipulá-los.
		 * Então chamaremos a Movimentacao de m no lugar de *, para dizer que queremos buscar estas movimentações.
		 */
		
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();
        
        Conta conta = new Conta();
        conta.setId(1L);
        
        
        /**
         * Para dizermos à JPA que a conta é um parâmetro, bastará inserirmos : na frente de seu nome e
         * transformarmos a primeira letra em maiúscula. Por convenção, a comunidade usuária da JPA costuma
         * adicionar também um p entre : e Conta nessa mesma linha, indicando mais claramente de que se
         * trata de um parâmetro, substituindo o nome "conta" por "pConta" em setParameter().
         */
        String jpql = "select m from Movimentacao m where m.conta = :pConta";

        Query query = em.createQuery(jpql);
        query.setParameter("pConta", conta);
        List<Movimentacao> resultList = query.getResultList();

        for (Movimentacao movimentacao : resultList) {
            System.out.println("Descrição: " + movimentacao.getDescricao());
            System.out.println("Tipo: " + movimentacao.getTipoMovimentacao());
        }
	}

}
