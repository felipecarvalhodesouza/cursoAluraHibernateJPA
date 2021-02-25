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
         * Sua caracter�stica � uma conta que existe na mem�ria, possui informa��es e 
         * n�o tem Id nenhum, mas pode se tornar Managed futuramente. (Bean)
         */

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        
        /**
         * Para fazer esta transforma��o, bastar� termos um EntityManager para inserirmos persist() passando a conta.
         * Quando executarmos a persist�ncia com este m�todo, estaremos transformando um objeto Transient em Managed,
         * cuja caracter�stica � a sincroniza��o autom�tica com o banco de dados.
         */
        //Transient -> Managed
        em.persist(conta);
        
        /**
         * Caso queiramos remover a conta Managed, poderemos usar o m�todo remove()
         * passando uma conta que ser� deletada de seu contexto JPA, o que gerar� a
         * sincroniza��o e aplicar� um delete no banco de dados, transformando-a em
         * um estado Removed.
         */
        //Managed -> Removed
        em.remove(conta);

        em.getTransaction().commit();
        
        /**
         * As a��es que a JPA executar� dentro do banco e as querys que digitar� e
         * disparar� contra o banco s�o consequ�ncias de transi��es de estados dentro
         * das entidades.
         */
	}

}
