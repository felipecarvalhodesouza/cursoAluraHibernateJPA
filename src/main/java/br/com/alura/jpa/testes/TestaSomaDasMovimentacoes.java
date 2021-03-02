package br.com.alura.jpa.testes;

import java.math.BigDecimal;

import br.com.alura.jpa.modelo.dao.MovimentacaoDao;

public class TestaSomaDasMovimentacoes {

	public static void main(String[] args) {

		BigDecimal somaDasMovimentacoes = new MovimentacaoDao().getSomaDasMovimentacoes();
		
		System.out.println("Soma das movimentações é: " + somaDasMovimentacoes);
		
		/*String jpql2 = "select avg(m.valor) from Movimentacao m";
		
		/**
		 * Método avg retorna por padrão Double
		 */
		/*TypedQuery<Double> query2 = em.createQuery(jpql2, Double.class);
		
		Double mediaDasMovimentacoes = query2.getSingleResult();
		
		System.out.println("Média das movimentações é: " + mediaDasMovimentacoes);*/
	}

}
