package br.com.senai.teste.service;
import org.springframework.stereotype.Service;
import br.com.senai.teste.model.Emprestimo;
import br.com.senai.teste.repository.EmprestimoRepository;
import java.util.Optional;
import java.util.List;


@Service
public class EmprestimoService {
    private final EmprestimoRepository emprestimoRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }

    public Emprestimo cadastrar(Emprestimo emprestimo) {
        return emprestimoRepository.save(emprestimo);
    }

    public List<Emprestimo> listar() {
        return emprestimoRepository.findAll();
    }

    public Optional<Emprestimo> buscarPorId(Integer id) {
        return emprestimoRepository.findById(id);
        
    }
    public Optional<Emprestimo> atualizar(Integer id, Emprestimo novosDados) {
        Optional<Emprestimo> emprestimoEncontrado = emprestimoRepository.findById(id);

        if (emprestimoEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Emprestimo emprestimo = emprestimoEncontrado.get();

        emprestimo.setDataEmprestimo(novosDados.getDataEmprestimo());
        emprestimo.setAluno(novosDados.getAluno());
        emprestimo.setLivro(novosDados.getLivro());

        return Optional.of(emprestimoRepository.save(emprestimo));
    }

}
