package br.com.senai.teste.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.teste.model.Emprestimo;
import br.com.senai.teste.service.EmprestimoService;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoControlle {
   
    private final EmprestimoService emprestimoService;

    public EmprestimoControlle(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping 
    public ResponseEntity<Emprestimo> cadastrar(@RequestBody Emprestimo emprestimo) {
        Optional<Emprestimo> emprestimoCadastrado = emprestimoService.cadastrar(
                emprestimo.getAluno().getId(),
                emprestimo.getLivro().getId());

        if (emprestimoCadastrado.isPresent()) {
            return ResponseEntity
                    .status(201)
                    .body(emprestimoCadastrado.get());
        } else {
            return ResponseEntity
                    .status(400)
                    .build();
        }
    }

    @PatchMapping("/{id}/devolver")
    public ResponseEntity<Emprestimo> devolver(@PathVariable Integer id) {
        Optional<Emprestimo> emprestimoOptional = emprestimoService.devolver(id);

        if (emprestimoOptional.isPresent()) {
            return ResponseEntity.ok(emprestimoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
