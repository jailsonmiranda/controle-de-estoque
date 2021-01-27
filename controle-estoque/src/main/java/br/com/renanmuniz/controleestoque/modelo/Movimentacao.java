package br.com.renanmuniz.controleestoque.modelo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Movimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produto produto;

    private Double quantidade;

    private LocalDateTime DataMovimentacao;

    private LocalDateTime DataAlteracao;

    public Movimentacao() {
    }

    public Movimentacao(Produto produto, Double quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getDataMovimentacao() {
        return DataMovimentacao;
    }

    public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
        DataMovimentacao = dataMovimentacao;
    }

    public LocalDateTime getDataAlteracao() {
        return DataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        DataAlteracao = dataAlteracao;
    }
}
