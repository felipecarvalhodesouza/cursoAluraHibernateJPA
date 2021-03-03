package br.com.alura.jpa.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import br.com.alura.jpa.modelo.Movimentacao;

public class TestaSomaDasMovimentacoes {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<BigDecimal> query = builder.createQuery(BigDecimal.class);
		
		
		/**
		 * Select m from Movimentacao m
		 * Essa é a raiz da query
		 */
		Root<Movimentacao> root = query.from(Movimentacao.class);
		
		/**
		 * sum(m.valor)
		 */
		Expression<BigDecimal> sum = builder.sum(root.<BigDecimal>get("valor"));
		
		query.select(sum);
		
		TypedQuery<BigDecimal> typedQuery = em.createQuery(query);
		
		System.out.println("Soma das movimentações é: " + typedQuery.getSingleResult());
		
		/*String jpql2 = "select avg(m.valor) from Movimentacao m";
		
		/**
		 * Método avg retorna por padrão Double
		 */
		/*TypedQuery<Double> query2 = em.createQuery(jpql2, Double.class);
		
		Double mediaDasMovimentacoes = query2.getSingleResult();
		
		System.out.println("Média das movimentações é: " + mediaDasMovimentacoes);*/
	}

}
