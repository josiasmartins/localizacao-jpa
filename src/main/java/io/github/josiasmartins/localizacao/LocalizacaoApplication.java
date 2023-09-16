package io.github.josiasmartins.localizacao;

import io.github.josiasmartins.localizacao.domain.entity.Cidade;
import io.github.josiasmartins.localizacao.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Inicializado!");
//		salvarCidade();
//		listarCidadesPorNome();

		this.listarCidadesPorQuantidadeHabitantes();
	}

	void listarCidadesPorQuantidadeHabitantes() {
		// TODO: verificr depois ->  No property 'lessThan' found for type 'Long'; Traversed path: Cidade.habitantes
		cidadeRepository.findByHabitantesLessThanAndNomeLike(1223343434L, "%o%").forEach(System.out::println);
	}

	@Transactional
	void salvarCidade() {
		var cidade = new Cidade(1L, "São Paulo", 12396372L);
		cidadeRepository.save(cidade);
	}

	void listarCidades() {
		this.cidadeRepository.findAll().forEach(System.out::println); // method reference
	}

	void listarCidadesPorNome() {
//		this.cidadeRepository.findByNome("Salvador").forEach(System.out::println); // method reference
		cidadeRepository.findByNomeLike("p%").forEach(System.out::println);
	}

	void listarCidadesPorHabitantes() {
		this.cidadeRepository.findByHabitantes(100000L).forEach(System.out::println); // method reference
	}

	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}

}
