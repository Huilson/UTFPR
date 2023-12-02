package br.com.eamais.pagamentos.dto;

import br.com.eamais.pagamentos.model.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoDto {
    private double valor;
    private String nome;
    private Long pedido;
}
