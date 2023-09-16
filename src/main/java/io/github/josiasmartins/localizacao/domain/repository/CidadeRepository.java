package io.github.josiasmartins.localizacao.domain.repository;


import io.github.josiasmartins.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
