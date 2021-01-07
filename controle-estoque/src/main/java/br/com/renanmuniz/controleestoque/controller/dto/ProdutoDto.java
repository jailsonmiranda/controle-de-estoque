package br.com.renanmuniz.controleestoque.controller.dto;

import br.com.renanmuniz.controleestoque.modelo.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDto {

    private Long id;

    private String nome;

    public ProdutoDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
    }

    public static List<ProdutoDto> converter(List<Produto> produtos) {
        return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
