package br.com.eamais.pagamentos.service;

import br.com.eamais.pagamentos.dto.PagamentoDto;
import br.com.eamais.pagamentos.http.PedidoClient;
import br.com.eamais.pagamentos.model.Pagamento;
import br.com.eamais.pagamentos.model.Status;
import br.com.eamais.pagamentos.repository.PagamentoRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PedidoClient pedido;
    
    //teste
    @PersistenceContext
    private EntityManager entityManager;

    public Page<PagamentoDto> obterTodos(Pageable paginacao) {
        return repository
                .findAll(paginacao)
                .map(p -> modelMapper.map(p, PagamentoDto.class));
    }

    public PagamentoDto obterPorId(Long id) {
        Pagamento pagamento = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public PagamentoDto criarPagamento(PagamentoDto dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);

        repository.save(pagamento);

        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public PagamentoDto atualizarPagamento(Long id, String status) {
        Pagamento pagamento = modelMapper.map(obterPorId(id), Pagamento.class);
        pagamento.setId(id);
        pagamento.setStatus(Enum.valueOf(Status.class, status));

        if (pagamento.getStatus().equals(Status.CONFIRMADO)) {
            Random random = new Random();

            if (random.nextInt(2) == 1) {
                pagamento.setStatus(Status.CONFIRMADO);
            } else {
                pagamento.setStatus(Status.CANCELADO);
            }
        }
        pagamento = repository.save(pagamento);
        confirmarPagamento(pagamento.getId());
        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public void excluirPagamento(Long id) {
        repository.deleteById(id);
    }

    public void confirmarPagamento(Long id) {
//        Optional<Pagamento> pagamento = repository.findById(id);
        Optional<Pagamento> pagamento = repository.findPagamentoByPedido(id);

        if (!pagamento.isPresent()) {
            throw new EntityNotFoundException();
        }

        pagamento.get().setStatus(Status.CONFIRMADO);
        repository.save(pagamento.get());
        pedido.aceitaPagamento((long) pagamento.get().getPedido());
    }

    public void cancelaPagamento(Long id) {
        Optional<Pagamento> pagamento = repository.findPagamentoByPedido(id);

        if (!pagamento.isPresent()) {
            throw new EntityNotFoundException();
        }

        pagamento.get().setStatus(Status.CANCELADO);
        repository.save(pagamento.get());
        pedido.cancelaPagamento((long) pagamento.get().getPedido());
    }

//    public void confirmarPagamento(Long id) {
//        Pagamento pagamento = findByPedidoId(id);
//
//        if (pagamento == null) {
//            throw new EntityNotFoundException();
//        }
//
//        pagamento.setStatus(Status.CONFIRMADO);
//        repository.save(pagamento);
//        pedido.aceitaPagamento((long) pagamento.getPedido());
//    }
//
//    public void cancelaPagamento(Long id) {
//        Pagamento pagamento = findByPedidoId(id);
//
//        if (pagamento == null) {
//            throw new EntityNotFoundException();
//        }
//
//        pagamento.setStatus(Status.CANCELADO);
//        repository.save(pagamento);
//        pedido.cancelaPagamento((long) pagamento.getPedido());
//    }
    
    public Pagamento findByPedidoId(Long id) {
        String jpql = "SELECT p FROM Pagamento p WHERE p.pedido = :id";
        List<Pagamento> pagamentos = entityManager.createQuery(jpql, Pagamento.class)
            .setParameter("pedido", id)
            .getResultList();

        return pagamentos.isEmpty() ? null : pagamentos.get(0);
    }
}