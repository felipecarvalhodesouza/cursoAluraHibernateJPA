package br.com.alura.jpa.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Movimentacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private BigDecimal valor;
	
	/**
	 * Em seguida, diremos à JPA que queremos salvar a String que caracteriza o enum salvo no banco;
	 * Então anotaremos @Enumerated() passando STRING como valor para salvarmos somente ENTRADA ou SAIDA.
	 */
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipoMovimentacao;
	private LocalDateTime data;
    private String descricao;
    
    /**
     * Para dizer a cardinalidade, sabemos que podem existir várias movimentações em uma única conta;
     * Então faremos uma anotação chamada @ManyToOne na classe Movimentacao, realizando o relacionamento.
     */
    @ManyToOne
    private Conta conta;
    
    @ManyToMany
    private List<Categoria> categoria;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}
	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
}
