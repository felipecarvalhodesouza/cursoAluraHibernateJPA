package br.com.alura.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Conta;

public class TestandoEstados {

	public static void main(String[] args) {
        //Transient
        Conta conta = new Conta();
        conta.setTitular("Almiro");
        conta.setNumero(321321);
        conta.setAgencia(123123);
        
        /**
         * A JPA tem o estado Transient para designar este tipo de objeto desvinculado.
         * Sua característica é uma conta que existe na memória, possui informações e 
         * não tem Id nenhum, mas pode se tornar Managed futuramente. (Bean)
         */

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        
        /**
         * Para fazer esta transformação, bastará termos um EntityManager para inserirmos persist() passando a conta.
         * Quando executarmos a persistência com este método, estaremos transformando um objeto Transient em Managed,
         * cuja característica é a sincronização automática com o banco de dados.
         */
        //Transient -> Managed
        em.persist(conta);
        
        /**
         * Caso queiramos remover a conta Managed, poderemos usar o método remove()
         * passando uma conta que será deletada de seu contexto JPA, o que gerará a
         * sincronização e aplicará um delete no banco de dados, transformando-a em
         * um estado Removed.
         */
        //Managed -> Removed
        em.remove(conta);

        em.getTransaction().commit();
        
        /**
         * As ações que a JPA executará dentro do banco e as querys que digitará e
         * disparará contra o banco são consequências de transições de estados dentro
         * das entidades.
         */
	}

}
