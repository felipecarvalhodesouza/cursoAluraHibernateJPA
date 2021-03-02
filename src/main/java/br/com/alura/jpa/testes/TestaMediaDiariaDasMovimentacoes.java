package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TestaMediaDiariaDasMovimentacoes {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();

		String jpql = "select avg(m.valor), day(m.data), month(m.data) from Movimentacao m group by day(m.data), month(m.data), year(m.data)";

		Query query = em.createQuery(jpql);

		List<Object[]> mediaDasMovimentacoes = query.getResultList();
		for (Object[] media : mediaDasMovimentacoes) {
			System.out.println("Média das movimentações do dia " + media[1] + "/" + media[2] + " é : " + media[0]);
		}
	}

}
