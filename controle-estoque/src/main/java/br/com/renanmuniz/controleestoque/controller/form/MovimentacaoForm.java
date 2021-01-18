package br.com.renanmuniz.controleestoque.controller.form;

import br.com.renanmuniz.controleestoque.modelo.Movimentacao;
import br.com.renanmuniz.controleestoque.modelo.Produto;
import br.com.renanmuniz.controleestoque.repository.ProdutoRepository;

import javax.validation.constraints.NotNull;
import java.util.NoSuchElementException;
import java.util.Optional;

public class MovimentacaoForm {

    @NotNull
    private Long produto_id;

    @NotNull
    private Double quantidade;

    public Long getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(Long produto_id) {
        this.produto_id = produto_id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Movimentacao converter(ProdutoRepository repository) throws NoSuchElementException{
        Optional<Produto> produtoOptional = repository.findById(produto_id);
        if(produtoOptional.isPresent()){
            Produto produto = produtoOptional.get();
            return new Movimentacao(produto,quantidade);
        }
        throw new NoSuchElementException(produtoOptional.toString());
    }
}
