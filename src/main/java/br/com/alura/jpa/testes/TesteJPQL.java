package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
        
        /**
         * Para fazer essa ordenação por valor em ordem decrescente, digitaremos order by 
         * pelo m.valor como desc após :pConta na linha de sql.
         */
        String jpql = "select m from Movimentacao m where m.conta = :pConta order by m.valor desc";
        
        /**
         * Na linha da lista, o getResultList() apresenta um warning do compilador indicando que o casting
         * feito entre List<> e Movimentacao não é checado; afinal, só é possível saber o tipo e conteúdo
         * da lista quando executarmos o código.
         */
        
        /**
         * Para evitar este erro, é comum colocarmos um tipo de query mais específica chamado TypedQuery<>,
         * o qual é geralmente tipado com o que foi trazido na query, e neste caso é a Movimentacao.
         * Portanto, todas as operações que fizermos já serão vistas como movimentações;
         * então passaremos o class da Movimentacao em createQuery() para retirar o aviso do sistema de getResultList().
         */
        
        TypedQuery<Movimentacao> query = em.createQuery(jpql, Movimentacao.class);
        query.setParameter("pConta", conta);
        List<Movimentacao> resultList = query.getResultList();

        for (Movimentacao movimentacao : resultList) {
            System.out.println("Descrição: " + movimentacao.getDescricao());
            System.out.println("Tipo: " + movimentacao.getTipoMovimentacao());
            System.out.println("Valor: " + movimentacao.getValor());
        }
	}

}
