package br.com.renanmuniz.controleestoque.repository;

import br.com.renanmuniz.controleestoque.modelo.Perfil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    List<Perfil> findByNome(String nome);

    Page<Perfil> findByNome(String nome, Pageable paginacao);
}
