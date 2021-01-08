package br.com.renanmuniz.controleestoque.repository;

import br.com.renanmuniz.controleestoque.modelo.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Page<Produto> findByNome(String nomeProduto, Pageable paginacao);

}
