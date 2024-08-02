package com.carreiras.java_spring_boot_pedidos.service;

import com.carreiras.java_spring_boot_pedidos.domain.entity.Cliente;
import com.carreiras.java_spring_boot_pedidos.domain.entity.ItemPedido;
import com.carreiras.java_spring_boot_pedidos.domain.entity.PagamentoComBoleto;
import com.carreiras.java_spring_boot_pedidos.domain.entity.Pedido;
import com.carreiras.java_spring_boot_pedidos.domain.enums.EstadoPagamento;
import com.carreiras.java_spring_boot_pedidos.domain.repository.ItemPedidoRepository;
import com.carreiras.java_spring_boot_pedidos.domain.repository.PedidoRepository;
import com.carreiras.java_spring_boot_pedidos.exception.AuthorizationException;
import com.carreiras.java_spring_boot_pedidos.exception.ObjectNotFoundException;
import com.carreiras.java_spring_boot_pedidos.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

import com.carreiras.java_spring_boot_pedidos.domain.repository.PagamentoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmailService emailService;

    public Pedido find(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id +
                ", Tipo: " + Pedido.class.getName()));
    }

    @Transactional
    public Pedido insert(Pedido pedido) {
        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.setCliente(clienteService.find(pedido.getCliente().getId()));
        pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);

        if (pedido.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, pedido.getInstante());
        }

        pedido = pedidoRepository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());

        for (ItemPedido ip : pedido.getItens()) {
            ip.setDesconto(0.0);
            ip.setProduto(produtoService.find(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(pedido);
        }

        itemPedidoRepository.saveAll(pedido.getItens());
//        emailService.sendOrderConfirmationEmail(pedido);
        emailService.sendOrderConfirmationHtmlEmail(pedido);
        return pedido;
    }

    public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        UserSS user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Acesso negado");
        }
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Cliente cliente =  clienteService.find(user.getId());
        return pedidoRepository.findByCliente(cliente, pageRequest);
    }
}
