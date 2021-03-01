package br.com.alura.jpa.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Conta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Integer agencia;
	private Integer numero;
	private String titular;
	private Double saldo;
	/**
	 * Relacionamento bidirecional.
	 * Precisa ser evidenciado que relacionamento é só um espelho
	 * Já foi mapeado pelo atributo 'conta' da movimentação
	 * 
	 * Eager é o contrário do lazy
	 * É o padrão de relacionamentos toOne
	 * Ele vai fazer o select mesmo se na impressão as movimentações
	 * estiverem comentadas.
	 * Busca antecipadamente os relacionamentos
	 */
	@OneToMany(mappedBy="conta", fetch = FetchType.EAGER)
	private List<Movimentacao> movimentacoes;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}
}
