package br.com.alura.jpa.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    
    /**
     * Por conven��o, o Hibernate ainda precisar� usar o construtor padr�o
     * da categoria para instanciar seus objetos; ent�o o criaremos sem argumentos
     * na classe Categoria que n�o executar� nenhuma a��o.
	 * Para indicar que este construtor ser� usado somente para a infraestrutura e
	 * n�o pelos desenvolvedores, escreveremos a anota��o @Deprecated.
     */
    @Deprecated
    public Categoria() {
    }
    
	public Categoria(String nome) {
		super();
		this.nome = nome;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}