package br.com.eamais.pagamentos.repository;

import br.com.eamais.pagamentos.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{
  
//  Optional<Pagamento> findByPedido_Id( Long pedidoId);


    Optional<Pagamento> findPagamentoByPedido(Long pedido);
}