package br.com.renanmuniz.controleestoque.controller;


import br.com.renanmuniz.controleestoque.controller.dto.PerfilDto;
import br.com.renanmuniz.controleestoque.controller.form.PerfilForm;
import br.com.renanmuniz.controleestoque.modelo.Perfil;
import br.com.renanmuniz.controleestoque.repository.PerfilRepository;
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
@RequestMapping("/perfis")
public class PerfilController {

    @Autowired
    private PerfilRepository PerfilRepository;

    /**
     *
     * @param nomePerfil
     * @return
     */
    @GetMapping
    public Page<PerfilDto> listar(@RequestParam(required = false) String nomePerfil,
                                      @PageableDefault(sort="id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                           Pageable paginacao) {
        if(nomePerfil == null) {
            Page<Perfil> perfis = PerfilRepository.findAll(paginacao);
            return PerfilDto.converter(perfis);
        } else {
            Page<Perfil> perfis = PerfilRepository.findByNome(nomePerfil,paginacao);
            return PerfilDto.converter(perfis);
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
    public ResponseEntity<PerfilDto> cadastrar(@RequestBody @Valid PerfilForm form,
                                                   UriComponentsBuilder uriBuilder) {
        Perfil Perfil = form.converter();
        Perfil.setDataCadastro(LocalDateTime.now());
        PerfilRepository.save(Perfil);

        URI uri = uriBuilder.path("/perfis/{id}").buildAndExpand(Perfil.getId()).toUri();
        return ResponseEntity.created(uri).body(new PerfilDto(Perfil));
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<PerfilDto> detalhar(@PathVariable Long id) {
        Optional<Perfil> PerfilOptional = PerfilRepository.findById(id);
        if(PerfilOptional.isPresent()) {
            return ResponseEntity.ok(new PerfilDto(PerfilOptional.get()));
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
    public ResponseEntity<PerfilDto> atualizar(@PathVariable Long id, @RequestBody @Valid PerfilForm form) {
        Optional<Perfil> PerfilOptional = PerfilRepository.findById(id);
        if(PerfilOptional.isPresent()) {
            Perfil Perfil = form.atualizar(id, PerfilRepository);
            Perfil.setDataAlteracao(LocalDateTime.now());
            return ResponseEntity.ok(new PerfilDto(Perfil));
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
        Optional<Perfil> PerfilOptional = PerfilRepository.findById(id);
        if(PerfilOptional.isPresent()) {
            PerfilRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
