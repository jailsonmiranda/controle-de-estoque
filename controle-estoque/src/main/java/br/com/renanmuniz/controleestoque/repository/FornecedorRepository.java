package br.com.renanmuniz.controleestoque.repository;

import br.com.renanmuniz.controleestoque.modelo.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    Fornecedor findByNome(String nome);
}
