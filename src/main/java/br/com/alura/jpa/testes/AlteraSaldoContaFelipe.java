package br.com.alura.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;

public class AlteraSaldoContaFelipe {

	public static void main(String[] args) {
		
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();
        
        Conta contaDoFelipe = em.find(Conta.class, 1L);
        
        em.getTransaction().begin();
        
        /**
         * Estranhamente, não estamos chamando nenhum método da JPA para fazer a alteração de estado.
         * A única ação que estamos fazendo neste código é alterar o estado no modelo, passando um novo saldo da mesma conta.
         */
        contaDoFelipe.setSaldo(20.0);

        em.getTransaction().commit();
        
        /**
         * Portanto, houve uma sincronização pelo simples fato de termos alterado o estado do modelo.
         * A seguir, entenderemos melhor como isso se acontece.
         */
	}

}
