package br.com.renanmuniz.controleestoque.controller.form;

import br.com.renanmuniz.controleestoque.modelo.Perfil;
import br.com.renanmuniz.controleestoque.modelo.Usuario;
import br.com.renanmuniz.controleestoque.repository.PerfilRepository;
import br.com.renanmuniz.controleestoque.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UsuarioForm {

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @NotEmpty
    private String senha;

    @NotNull
    @NotEmpty
    private String nomePerfil;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomePerfil() {
        return nomePerfil;
    }

    public void setNomePerfil(String nomePerfil) {
        this.nomePerfil = nomePerfil;
    }

    public Usuario converter(PerfilRepository repository) {
        List<Perfil> perfil = repository.findByNome("ROLE_" + nomePerfil.toUpperCase());
        String senhaCript = new BCryptPasswordEncoder().encode(this.senha);
        return new Usuario(nome,senhaCript,perfil);
    }

    public Usuario atualizar(Long id, UsuarioRepository usuarioRepository, PerfilRepository perfilRepository) {
        Usuario usuario = usuarioRepository.getOne(id);
        usuario.setNome(this.nome);
        usuario.setSenha(new BCryptPasswordEncoder().encode(this.senha));

        List<Perfil> perfil = perfilRepository.findByNome("ROLE_" + nomePerfil.toUpperCase());
        usuario.setPerfis(perfil);
        return usuario;
    }
}
