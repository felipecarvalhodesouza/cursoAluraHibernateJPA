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
		 *  Nenhuma atualiza��o acontece depois que fechamos com em.close();
		 *  Todas as contas que antes estavam em estado Managed pelo pr�prio o EntityManager
		 *  passam a ser Detached quando este � fechado.
		 */
        EntityManager em2 = emf.createEntityManager();
        
        conta.setSaldo(100.0);
        
        em2.getTransaction().begin();
        
        /**
         * Ent�o se temos uma conta que � Detached neste momento, precisaremos alterar seu estado para o anterior.
         * Para isso, criaremos mais um EntityManager ap�s seu fechamento para podermos aplicar em2.merge() com a conta,
         * transformando-a em Managed novamente.
		 * Com isso, a verifica��o por cada atributo acontecer� de novo at� encontrar o que foi alterado,
		 * para ent�o um update ser lan�ado no banco.
         */
        em2.merge(conta);
        
        em2.getTransaction().commit();
        
        /**
         * Ent�o deveremos ter em mente que todas as querys disparadas contra o database 
         * s�o consequ�ncia da transi��o de estados.
         */
	}

}
