package br.com.renanmuniz.controleestoque.controller.form;

import br.com.renanmuniz.controleestoque.modelo.Fornecedor;
import br.com.renanmuniz.controleestoque.modelo.Produto;
import br.com.renanmuniz.controleestoque.repository.FornecedorRepository;
import br.com.renanmuniz.controleestoque.repository.ProdutoRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProdutoForm {

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @NotEmpty
    private String nomeFornecedor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public Produto converter(FornecedorRepository repository) {
        Fornecedor fornecedor = repository.findByNome(nomeFornecedor);
        return new Produto(nome,fornecedor);
    }

    public Produto atualizar(Long id, ProdutoRepository produtoRepository, FornecedorRepository repository) {
        Produto produto = produtoRepository.getOne(id);

        produto.setNome(this.nome);
        Fornecedor fornecedor = repository.findByNome(nomeFornecedor);
        produto.setFornecedor(fornecedor);

        return produto;
    }
}
