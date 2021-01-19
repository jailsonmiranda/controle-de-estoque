package br.com.renanmuniz.controleestoque.controller.dto;

import br.com.renanmuniz.controleestoque.modelo.Perfil;
import org.springframework.data.domain.Page;

public class PerfilDto {

    private Long id;

    private String nome;

    public PerfilDto(Perfil perfil) {
        this.id = perfil.getId();
        this.nome = perfil.getNome();
    }

    public static Page<PerfilDto> converter(Page<Perfil> perfis) {
        return perfis.map(PerfilDto::new);
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
