package br.com.renanmuniz.controleestoque.controller.form;

import br.com.renanmuniz.controleestoque.modelo.Perfil;
import br.com.renanmuniz.controleestoque.repository.PerfilRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PerfilForm {

    @NotNull
    @NotEmpty
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Perfil converter() {
        return new Perfil(nome);
    }

    public Perfil atualizar(Long id, PerfilRepository repository) {
        Perfil perfil = repository.getOne(id);
        perfil.setNome(this.nome);

        return perfil;
    }
}
