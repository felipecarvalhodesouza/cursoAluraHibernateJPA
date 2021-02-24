package br.com.alura.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;

public class CriaConta {

	public static void main(String[] args) {
		
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();
        
		Conta conta = new Conta();
		conta.setTitular("Felipe");
		conta.setNumero(1234);
		conta.setAgencia(4321);
		
		/**
		 * Todo o comando de mudança de estado que executamos na JPA para fazer um insert,
		 * alterar ou remover algo em nosso banco, será necessário que esteja obrigatoriamente dentro de uma transação.
		 * A transação é um escopo de tarefas que serão executadas de uma forma única ou atômica.
		 * Caso alguma das operações apresentar algum problema, bastará voltar ao estado anterior chamado de rollback.
		 */
		em.getTransaction().begin();
		
		em.persist(conta);
		
		em.getTransaction().commit();
	}
}