package io.github.josiasmartins.localizacao;

import io.github.josiasmartins.localizacao.domain.entity.Cidade;
import io.github.josiasmartins.localizacao.domain.repository.CidadeRepository;
import io.github.josiasmartins.localizacao.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	@Autowired
	private CidadeService cidadeService;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Inicializado!");
//		salvarCidade();
//		listarCidadesPorNome();

		var cidade = new Cidade(1L, "porto Alegre", 100L);
		cidadeService.listarCidadesSpecsFiltroDinamico(cidade);

//		cidadeService.listarCidadesByNomeSpecs();

//		this.cidadeService.listarCidadesPorNome();

	}

	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}

}
