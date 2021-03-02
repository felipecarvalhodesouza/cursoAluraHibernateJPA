package br.com.alura.jpa.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.dao.MovimentacaoDao;

public class TestaSomaDasMovimentacoes {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();

		BigDecimal somaDasMovimentacoes = new MovimentacaoDao(em).getSomaDasMovimentacoes();
		
		System.out.println("Soma das movimenta��es �: " + somaDasMovimentacoes);
		
		/*String jpql2 = "select avg(m.valor) from Movimentacao m";
		
		/**
		 * M�todo avg retorna por padr�o Double
		 */
		/*TypedQuery<Double> query2 = em.createQuery(jpql2, Double.class);
		
		Double mediaDasMovimentacoes = query2.getSingleResult();
		
		System.out.println("M�dia das movimenta��es �: " + mediaDasMovimentacoes);*/
	}

}
