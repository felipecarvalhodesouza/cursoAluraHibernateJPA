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
		 * Todo o comando de mudan�a de estado que executamos na JPA para fazer um insert,
		 * alterar ou remover algo em nosso banco, ser� necess�rio que esteja obrigatoriamente dentro de uma transa��o.
		 * A transa��o � um escopo de tarefas que ser�o executadas de uma forma �nica ou at�mica.
		 * Caso alguma das opera��es apresentar algum problema, bastar� voltar ao estado anterior chamado de rollback.
		 */
		em.getTransaction().begin();
		
		em.persist(conta);
		
		em.getTransaction().commit();
	}
}