package br.com.renanmuniz.controleestoque.controller;


import br.com.renanmuniz.controleestoque.controller.dto.FornecedorDto;
import br.com.renanmuniz.controleestoque.controller.form.FornecedorForm;
import br.com.renanmuniz.controleestoque.modelo.Fornecedor;
import br.com.renanmuniz.controleestoque.repository.FornecedorRepository;
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
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    /**
     *
     * @param nomeFornecedor
     * @return
     */
    @GetMapping
    public Page<FornecedorDto> listar(@RequestParam(required = false) String nomeFornecedor,
                                      @PageableDefault(sort="id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                           Pageable paginacao) {
        if(nomeFornecedor == null) {
            Page<Fornecedor> fornecedores = fornecedorRepository.findAll(paginacao);
            return FornecedorDto.converter(fornecedores);
        } else {
            Page<Fornecedor> fornecedores = fornecedorRepository.findByNome(nomeFornecedor,paginacao);
            return FornecedorDto.converter(fornecedores);
        }
    }

    /**
     *
     * @param form
     * @param uriBuilder
     * @return
     */
    @PostMapping
    @Transactional
    public ResponseEntity<FornecedorDto> cadastrar(@RequestBody @Valid FornecedorForm form,
                                                   UriComponentsBuilder uriBuilder) {
        Fornecedor fornecedor = form.converter();
        fornecedor.setDataCadastro(LocalDateTime.now());
        fornecedorRepository.save(fornecedor);

        URI uri = uriBuilder.path("/fornecedores/{id}").buildAndExpand(fornecedor.getId()).toUri();
        return ResponseEntity.created(uri).body(new FornecedorDto(fornecedor));
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<FornecedorDto> detalhar(@PathVariable Long id) {
        Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findById(id);
        if(fornecedorOptional.isPresent()) {
            return ResponseEntity.ok(new FornecedorDto(fornecedorOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }


    /**
     *
     * @param id
     * @param form
     * @return
     */
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<FornecedorDto> atualizar(@PathVariable Long id, @RequestBody @Valid FornecedorForm form) {
        Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findById(id);
        if(fornecedorOptional.isPresent()) {
            Fornecedor fornecedor = form.atualizar(id, fornecedorRepository);
            fornecedor.setDataAlteracao(LocalDateTime.now());
            return ResponseEntity.ok(new FornecedorDto(fornecedor));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findById(id);
        if(fornecedorOptional.isPresent()) {
            fornecedorRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
