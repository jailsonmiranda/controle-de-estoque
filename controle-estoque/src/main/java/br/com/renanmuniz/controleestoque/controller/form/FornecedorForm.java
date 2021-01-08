package br.com.renanmuniz.controleestoque.controller.form;

import br.com.renanmuniz.controleestoque.modelo.Fornecedor;
import br.com.renanmuniz.controleestoque.repository.FornecedorRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class FornecedorForm {

    @NotNull
    @NotEmpty
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Fornecedor converter() {
        return new Fornecedor(nome);
    }

    public Fornecedor atualizar(Long id, FornecedorRepository repository) {
          Fornecedor fornecedor = repository.getOne(id);
          fornecedor.setNome(this.nome);

        return fornecedor;
    }
}
