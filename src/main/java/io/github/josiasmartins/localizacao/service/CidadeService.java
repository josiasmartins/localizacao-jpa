package io.github.josiasmartins.localizacao.service;

import io.github.josiasmartins.localizacao.domain.entity.Cidade;
import io.github.josiasmartins.localizacao.domain.repository.CidadeRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CidadeService {

    private CidadeRepository repository;

    public CidadeService(CidadeRepository repository) {
        this.repository = repository;
    }

    void listarCidadesPorQuantidadeHabitantes() {
        // TODO: verificr depois ->  No property 'lessThan' found for type 'Long'; Traversed path: Cidade.habitantes
//        repository.findByHabitantesLessThanAndNomeLike(1223343434L, "%o%").forEach(System.out::println);
    }

    @Transactional
    public void salvarCidade() {
        var cidade = new Cidade(1L, "São Paulo", 12396372L);
        repository.save(cidade);
    }

    public void listarCidades() {
        this.repository.findAll().forEach(System.out::println); // method reference
    }

    public void listarCidadesPorNome() {
//		repository.findByNome("Salvador").forEach(System.out::println); // method reference
        Pageable pageable = PageRequest.of(1, 2); // cria um pageable
        repository
                .findByNomeLike("%%%%%", pageable) // Sort: ordena
                .forEach(System.out::println);
    }

    public void listarCidadesPorHabitantes() {
        this.repository.findByHabitantes(100000L).forEach(System.out::println); // method reference
    }

    public List<Cidade> filtroDinamico(Cidade cidade) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase() // ignora o case sensitive
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING); // comeca somente com esses valores passados;

        Example<Cidade> example = Example.of(cidade, matcher);
        return repository.findAll(example);
//        return repository
//                .findByHabitantesLessThanAndNomeLike(cidade.getHabitantes(), cidade.getNome());
    }

}
