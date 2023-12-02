package br.com.eamais.pagamentos.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("pedidos-ms")
public interface PedidoClient {
    @RequestMapping(method = RequestMethod.PUT, value = "/pedidos/{id}/pagamentoOk")
    void aceitaPagamento(@PathVariable Long id);

    @RequestMapping(method = RequestMethod.PUT, value = "/pedidos/{id}/pagamentoOff")
    void cancelaPagamento(@PathVariable Long id);
}
