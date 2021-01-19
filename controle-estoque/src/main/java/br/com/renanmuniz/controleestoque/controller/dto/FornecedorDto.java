package br.com.renanmuniz.controleestoque.controller.dto;

import br.com.renanmuniz.controleestoque.modelo.Fornecedor;
import org.springframework.data.domain.Page;

public class FornecedorDto {

    private Long id;

    private String nome;

    public FornecedorDto(Fornecedor fornecedor) {
        this.id = fornecedor.getId();
        this.nome = fornecedor.getNome();
    }

    public static Page<FornecedorDto> converter(Page<Fornecedor> fornecedores) {
        return fornecedores.map(FornecedorDto::new);
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
