package br.com.alura.jpa.testes;

import java.util.List;

import br.com.alura.jpa.modelo.MediaComData;
import br.com.alura.jpa.modelo.dao.MovimentacaoDao;

public class TestaMediaDiariaDasMovimentacoes {

	public static void main(String[] args) {

		List<MediaComData> mediaDasMovimentacoes = new MovimentacaoDao().getMediaDiariaDasMovimentacoes();
				
		for (MediaComData media : mediaDasMovimentacoes) {
			System.out.println("Média das movimentações do dia " + media.getDia() + "/" + media.getMes() + " é : " + media.getValor());
		}
	}

}
