package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.alura.jpa.modelo.Categoria;
import br.com.alura.jpa.modelo.Movimentacao;

public class TesteJPQLMovimentacaoDeUmaCategoria {

	public static void main(String[] args) {
		
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
        EntityManager em = emf.createEntityManager();
        
        /**
         * Fazemos o filtro com a m.conta em nossa query, pois em nossa classe Movimentacao temos um atributo conta
         * cujo relacionamento é de Muitos para Um, portanto há várias movimentações que pertencem à uma única conta.
         * 
         * No caso de Categoria, temos uma lista de categorias em um relacionamento Muitos para Muitos.
		 * Essas categorias não estão armazenadas na tabela Movimentacao, e sim na Categoria.
		 * 
		 * Também existe uma de relacionamento que faz a correlação entre chaves estrangeiras.
		 * 
		 * Portanto, precisaremos juntar essas duas últimas tabelas citadas para fazer esta busca.
		 * 
		 * Utilizaremos uma palavra-chave chamada join, a qual é responsável pela junção com m.categorias nomeada como
		 * c quando trabalhamos com tabela de relacionamento.
         */
        String sql = "select m from Movimentacao m join m.categorias c where c = :pCategoria";

        Categoria categoria = new Categoria();
        categoria.setId(4L);

        TypedQuery<Movimentacao> query = em.createQuery(sql, Movimentacao.class);
        query.setParameter("pCategoria", categoria);

        List<Movimentacao> movimentacoes = query.getResultList();
        for (Movimentacao movimentacao : movimentacoes) {
        	System.out.println("Categorias: " + movimentacao.getCategorias());
            System.out.println("Descrição: " + movimentacao.getDescricao());
            System.out.println("Valor: " + movimentacao.getValor());
            System.out.println("Tipo: " + movimentacao.getTipoMovimentacao());
        }
    }
}
