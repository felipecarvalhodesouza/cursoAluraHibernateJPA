package br.com.alura.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;

public class CriaContaComSaldo {

	public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();
        
		Conta conta = new Conta();
		conta.setTitular("Rogelio");
		conta.setNumero(12345);
		conta.setAgencia(54321);
		conta.setSaldo(500.0);
		
		em.getTransaction().begin();
		
		em.persist(conta);
		
		em.getTransaction().commit();
		em.close();
				
		/**
		 *  Nenhuma atualização acontece depois que fechamos com em.close();
		 *  Todas as contas que antes estavam em estado Managed pelo próprio o EntityManager
		 *  passam a ser Detached quando este é fechado.
		 */
        EntityManager em2 = emf.createEntityManager();
        
        conta.setSaldo(100.0);
        
        em2.getTransaction().begin();
        
        /**
         * Então se temos uma conta que é Detached neste momento, precisaremos alterar seu estado para o anterior.
         * Para isso, criaremos mais um EntityManager após seu fechamento para podermos aplicar em2.merge() com a conta,
         * transformando-a em Managed novamente.
		 * Com isso, a verificação por cada atributo acontecerá de novo até encontrar o que foi alterado,
		 * para então um update ser lançado no banco.
         */
        em2.merge(conta);
        
        em2.getTransaction().commit();
        
        /**
         * Então deveremos ter em mente que todas as querys disparadas contra o database 
         * são consequência da transição de estados.
         */
	}

}
