package io.github.josiasmartins.localizacao.domain.repository;


import io.github.josiasmartins.localizacao.domain.entity.Cidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    // busca pelo nome correto
    List<Cidade> findByNome(String nome);

    // busca pelo nome comecando por aquele pedaço
    List<Cidade> findByNomeStartingWith(String nome);

    // busca pelo nome terminando por aquele pedaço
    List<Cidade> findByNomeEndingWith(String nome);

    // busca pelo like
    @Query(" select c from Cidade c where upper(c.nome) like upper(?1) ") // ?!: primeiro parametro que vier na query
    List<Cidade> findByNomeLike(String nome);

    // busca pelo nome like ordenado
    @Query(" select c from Cidade c where upper(c.nome) like upper(?1) ") // ?!: primeiro parametro que vier na query
    List<Cidade> findByNomeLike(String nome, Sort sort);

    // busca pelo nome like paginacao
    @Query(" select c from Cidade c where upper(c.nome) like upper(?1) ") // ?!: primeiro parametro que vier na query
    Page<Cidade> findByNomeLike(String nome, Pageable pageable);

    // busca pelo nome contendo aquele pedaço
    List<Cidade> findByNomeContaining(String nome);

    List<Cidade> findByHabitantes(Long habitantes);

    List<Cidade> findByHabitantesLessThan(Long habitantes);

    List<Cidade> findByHabitantesGreaterThan(Long habitantes);

//    List<Cidade> findByHabitantesLessThanEquals(Long habitantes); // TODO: exception sql

    List<Cidade> findByHabitantesLessThanAndNomeLike(Long habitantes, String nome);
}
