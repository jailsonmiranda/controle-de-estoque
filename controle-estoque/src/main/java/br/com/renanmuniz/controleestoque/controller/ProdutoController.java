package br.com.renanmuniz.controleestoque.controller;


import br.com.renanmuniz.controleestoque.controller.dto.ProdutoDto;
import br.com.renanmuniz.controleestoque.controller.form.ProdutoForm;
import br.com.renanmuniz.controleestoque.modelo.Produto;
import br.com.renanmuniz.controleestoque.repository.FornecedorRepository;
import br.com.renanmuniz.controleestoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @GetMapping
    public List<ProdutoDto> lista(String nomeProduto) {
        if(nomeProduto == null) {
            List<Produto> produtos = produtoRepository.findAll();
            return ProdutoDto.converter(produtos);
        } else {
            List<Produto> produtos = produtoRepository.findByNome(nomeProduto);
            return ProdutoDto.converter(produtos);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoForm form, UriComponentsBuilder uriBuilder) {
        Produto produto = form.converter(fornecedorRepository);
        produtoRepository.save(produto);

        URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoDto(produto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> detalhar(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isPresent()) {
            return ResponseEntity.ok(new ProdutoDto(produto.get()));
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id, @RequestBody @Valid ProdutoForm form) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if(produtoOptional.isPresent()) {
            Produto produto = form.atualizar(id, produtoRepository, fornecedorRepository);
            return ResponseEntity.ok(new ProdutoDto(produto));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if(produtoOptional.isPresent()) {
            produtoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
