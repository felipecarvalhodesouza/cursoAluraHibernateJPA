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
		 * Neste cen�rio em que usamos JPA em nosso projeto, queremos nos afastar ao m�ximo do mundo relacional
		 * Quando criarmos objetos no mundo Java, os atribuiremos a vari�veis para podermos manipul�-los.
		 * Ent�o chamaremos a Movimentacao de m no lugar de *, para dizer que queremos buscar estas movimenta��es.
		 */
		
		/**
		 * Quando criarmos objetos no mundo Java, os atribuiremos a vari�veis para podermos manipul�-los.
		 * Ent�o chamaremos a Movimentacao de m no lugar de *, para dizer que queremos buscar estas movimenta��es.
		 */
		
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();
        
        Conta conta = new Conta();
        conta.setId(1L);
        
        
        /**
         * Para dizermos � JPA que a conta � um par�metro, bastar� inserirmos : na frente de seu nome e
         * transformarmos a primeira letra em mai�scula. Por conven��o, a comunidade usu�ria da JPA costuma
         * adicionar tamb�m um p entre : e Conta nessa mesma linha, indicando mais claramente de que se
         * trata de um par�metro, substituindo o nome "conta" por "pConta" em setParameter().
         */
        
        /**
         * Para fazer essa ordena��o por valor em ordem decrescente, digitaremos order by 
         * pelo m.valor como desc ap�s :pConta na linha de sql.
         */
        String jpql = "select m from Movimentacao m where m.conta = :pConta order by m.valor desc";
        
        /**
         * Na linha da lista, o getResultList() apresenta um warning do compilador indicando que o casting
         * feito entre List<> e Movimentacao n�o � checado; afinal, s� � poss�vel saber o tipo e conte�do
         * da lista quando executarmos o c�digo.
         */
        
        /**
         * Para evitar este erro, � comum colocarmos um tipo de query mais espec�fica chamado TypedQuery<>,
         * o qual � geralmente tipado com o que foi trazido na query, e neste caso � a Movimentacao.
         * Portanto, todas as opera��es que fizermos j� ser�o vistas como movimenta��es;
         * ent�o passaremos o class da Movimentacao em createQuery() para retirar o aviso do sistema de getResultList().
         */
        
        TypedQuery<Movimentacao> query = em.createQuery(jpql, Movimentacao.class);
        query.setParameter("pConta", conta);
        List<Movimentacao> resultList = query.getResultList();

        for (Movimentacao movimentacao : resultList) {
            System.out.println("Descri��o: " + movimentacao.getDescricao());
            System.out.println("Tipo: " + movimentacao.getTipoMovimentacao());
            System.out.println("Valor: " + movimentacao.getValor());
        }
	}

}
