package br.com.renanmuniz.controleestoque.controller;


import br.com.renanmuniz.controleestoque.controller.dto.MovimentacaoDto;
import br.com.renanmuniz.controleestoque.controller.dto.ProdutoDto;
import br.com.renanmuniz.controleestoque.controller.form.MovimentacaoForm;
import br.com.renanmuniz.controleestoque.controller.form.ProdutoForm;
import br.com.renanmuniz.controleestoque.modelo.Movimentacao;
import br.com.renanmuniz.controleestoque.modelo.Produto;
import br.com.renanmuniz.controleestoque.repository.FornecedorRepository;
import br.com.renanmuniz.controleestoque.repository.MovimentacaoRepository;
import br.com.renanmuniz.controleestoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;


@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

//    /**
//     *
//     * @param nomeProduto
//     * @return
//     */
//    @GetMapping
//    public Page<ProdutoDto> listar(@RequestParam(required = false) String nomeProduto,
//                                   @PageableDefault(sort="id", direction = Sort.Direction.ASC, page = 0, size = 10)
//                                           Pageable paginacao) {
//        if(nomeProduto == null) {
//            Page<Produto> produtos = produtoRepository.findAll(paginacao);
//            return ProdutoDto.converter(produtos);
//        } else {
//            Page<Produto> produtos = produtoRepository.findByNome(nomeProduto, paginacao);
//            return ProdutoDto.converter(produtos);
//        }
//    }
//
    /**
     *
     * @param form
     * @param uriBuilder
     * @return
     */
    @PostMapping
    @Transactional
    public ResponseEntity<MovimentacaoDto> cadastrar(@RequestBody @Valid MovimentacaoForm form,
                                                     UriComponentsBuilder uriBuilder) {
        Movimentacao movimentacao = form.converter(produtoRepository);
        movimentacao.setDataMovimentacao(LocalDateTime.now());
        movimentacaoRepository.save(movimentacao);

        URI uri = uriBuilder.path("/movimentacoes/{id}").buildAndExpand(movimentacao.getId()).toUri();
        return ResponseEntity.created(uri).body(new MovimentacaoDto(movimentacao));
    }
//
//    /**
//     *
//     * @param id
//     * @return
//     */
//    @GetMapping("/{id}")
//    public ResponseEntity<ProdutoDto> detalhar(@PathVariable Long id) {
//        Optional<Produto> produtoOptional = produtoRepository.findById(id);
//        if(produtoOptional.isPresent()) {
//            return ResponseEntity.ok(new ProdutoDto(produtoOptional.get()));
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//
//    /**
//     *
//     * @param id
//     * @param form
//     * @return
//     */
//    @PutMapping("/{id}")
//    @Transactional
//    public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id, @RequestBody @Valid ProdutoForm form) {
//        Optional<Produto> produtoOptional = produtoRepository.findById(id);
//        if(produtoOptional.isPresent()) {
//            Produto produto = form.atualizar(id, produtoRepository, fornecedorRepository);
//            produto.setDataAlteracao(LocalDateTime.now());
//            return ResponseEntity.ok(new ProdutoDto(produto));
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    /**
//     *
//     * @param id
//     * @return
//     */
//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity<?> remover(@PathVariable Long id) {
//        Optional<Produto> produtoOptional = produtoRepository.findById(id);
//        if(produtoOptional.isPresent()) {
//            produtoRepository.deleteById(id);
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.notFound().build();
//    }

}
