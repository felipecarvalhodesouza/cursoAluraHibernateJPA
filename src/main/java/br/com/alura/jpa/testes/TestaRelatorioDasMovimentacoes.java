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
         * Não fazer um select pra cada relacionamento, usar o join
         */
        String jpql = "select c from Conta c left join fetch c.movimentacoes";
        
        TypedQuery<Conta> query = em.createQuery(jpql, Conta.class);
        
        List<Conta> contas = query.getResultList();
        
        for (Conta conta : contas) {
        	System.out.println("Titular: " + conta.getTitular());
        	System.out.println("Agência: " + conta.getAgencia());
        	System.out.println("Número: " + conta.getNumero());
        	/**
        	 * Necessário imprimir a query para ver a diferença
        	 * As movimentações só são buscadas no banco quando vão ser impressas.	
        	 * Na JPA, todos os relacionamentos toMany são, por padrão, carregados preguiçosamente
        	 * Carregados conforme são utilizados.
        	 * Lazy loading
        	 */
        	System.out.println("Movimentações: " + conta.getMovimentacoes());			
		}
        
        /**
         * Esse é o cenário N+1
         * Pois primeiro é feito um select para buscar a conta
         * E para cada conta, são feito N selects para buscar as movimentações
         * 
         * Não é bom performaticamente num sistema real
         */
	}

}
