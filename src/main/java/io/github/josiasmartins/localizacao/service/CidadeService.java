package io.github.josiasmartins.localizacao.service;

import io.github.josiasmartins.localizacao.domain.entity.Cidade;
import io.github.josiasmartins.localizacao.domain.repository.CidadeRepository;
import io.github.josiasmartins.localizacao.domain.repository.specs.CidadeSpecs;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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

    public void listarCidadesByNomeSpecs() {
        Specification<Cidade> spec = CidadeSpecs
                .propertyEqual("nome", "sao paulo")
                        .and(CidadeSpecs.propertyEqual("habitantes", 1000L));
//                .nomeEqual("São Paulo")
//                .or(CidadeSpecs.habitantesGreaterThan(1000));
//                .and(CidadeSpecs.habitantesGreaterThan(1000));
        repository.findAll(spec).forEach(System.out::println);
    }

    public void listarCidadesSpecsFiltroDinamico(Cidade filtro) {
        Specification<Cidade> specs = Specification
                .where((root, query, criteriaBuilder) -> criteriaBuilder.conjunction()); // select * from cidade where 1 = 1 conjunction

        if (filtro.getId() != null) {
            specs = specs.and(CidadeSpecs.idEqual(filtro.getId()));
        }

        if (StringUtils.hasText(filtro.getNome())) {
            specs = specs.and(CidadeSpecs.nomeLike(filtro.getNome()));
        }

        if (filtro.getHabitantes() != null) {
            specs = specs.and(CidadeSpecs.habitantesGreaterThan(filtro.getHabitantes()));
        }

        repository.findAll(specs).forEach(System.out::println);
    }

}
