package br.com.renanmuniz.controleestoque.controller.dto;

import br.com.renanmuniz.controleestoque.modelo.Movimentacao;
import br.com.renanmuniz.controleestoque.modelo.Produto;
import org.springframework.data.domain.Page;

public class MovimentacaoDto {

    private Long id;
    private Double quantidade;
    private Long produto_id;
    private String produto_nome;

    public MovimentacaoDto(Movimentacao movimentacao) {
        this.id = movimentacao.getId();
        this.quantidade = movimentacao.getQuantidade();
        this.produto_id = movimentacao.getProduto().getId();
        this.produto_nome = movimentacao.getProduto().getNome();
    }

    public static Page<MovimentacaoDto> converter(Page<Movimentacao> movimentacoes) {
        return movimentacoes.map(MovimentacaoDto::new);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Long getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(Long produto_id) {
        this.produto_id = produto_id;
    }

    public String getProduto_nome() {
        return produto_nome;
    }

    public void setProduto_nome(String produto_nome) {
        this.produto_nome = produto_nome;
    }
}
