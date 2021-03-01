package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.alura.jpa.modelo.Conta;

public class TestaRelatorioDasMovimentacoes {

	public static void main(String[] args) {
		
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();
        
        /**
         * Solucionar o problema de performance das buscas N+1
         * N�o fazer um select pra cada relacionamento, usar o join
         */
        String jpql = "select c from Conta c left join fetch c.movimentacoes";
        
        TypedQuery<Conta> query = em.createQuery(jpql, Conta.class);
        
        List<Conta> contas = query.getResultList();
        
        for (Conta conta : contas) {
        	System.out.println("Titular: " + conta.getTitular());
        	System.out.println("Ag�ncia: " + conta.getAgencia());
        	System.out.println("N�mero: " + conta.getNumero());
        	/**
        	 * Necess�rio imprimir a query para ver a diferen�a
        	 * As movimenta��es s� s�o buscadas no banco quando v�o ser impressas.	
        	 * Na JPA, todos os relacionamentos toMany s�o, por padr�o, carregados pregui�osamente
        	 * Carregados conforme s�o utilizados.
        	 * Lazy loading
        	 */
        	System.out.println("Movimenta��es: " + conta.getMovimentacoes());			
		}
        
        /**
         * Esse � o cen�rio N+1
         * Pois primeiro � feito um select para buscar a conta
         * E para cada conta, s�o feito N selects para buscar as movimenta��es
         * 
         * N�o � bom performaticamente num sistema real
         */
	}

}
