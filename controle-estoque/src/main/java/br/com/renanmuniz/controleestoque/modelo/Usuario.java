package br.com.renanmuniz.controleestoque.modelo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String senha;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAlteracao;

    @ManyToMany(fetch = FetchType.EAGER)
    @NotNull
    private List<Perfil> perfis = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (id != null ? !id.equals(usuario.id) : usuario.id != null) return false;
        if (nome != null ? !nome.equals(usuario.nome) : usuario.nome != null) return false;
        if (senha != null ? !senha.equals(usuario.senha) : usuario.senha != null) return false;
        if (dataCadastro != null ? !dataCadastro.equals(usuario.dataCadastro) : usuario.dataCadastro != null)
            return false;
        if (dataAlteracao != null ? !dataAlteracao.equals(usuario.dataAlteracao) : usuario.dataAlteracao != null)
            return false;
        return perfis != null ? perfis.equals(usuario.perfis) : usuario.perfis == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (senha != null ? senha.hashCode() : 0);
        result = 31 * result + (dataCadastro != null ? dataCadastro.hashCode() : 0);
        result = 31 * result + (dataAlteracao != null ? dataAlteracao.hashCode() : 0);
        result = 31 * result + (perfis != null ? perfis.hashCode() : 0);
        return result;
    }

    // Construtor padrão para JPA
    public Usuario() {
    }

    public Usuario(String nome, String senha, List<Perfil> perfis) {
        this.nome = nome;
        this.senha = senha;
        this.perfis = perfis;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
