package br.com.renanmuniz.controleestoque.repository;

import br.com.renanmuniz.controleestoque.modelo.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
}
