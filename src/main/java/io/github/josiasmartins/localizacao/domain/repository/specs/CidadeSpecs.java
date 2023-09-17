package io.github.josiasmartins.localizacao.domain.repository.specs;

import io.github.josiasmartins.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.domain.Specification;

public abstract class CidadeSpecs {

    public static  Specification<Cidade> propertyEqual(String prop, Object value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(prop), value);
    }

    public static Specification<Cidade> nomeEqual(String nome) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("nome"), nome);
    }

    public static Specification<Cidade> habitantesGreaterThan(Integer value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("habitantes"), value);
    }

}
