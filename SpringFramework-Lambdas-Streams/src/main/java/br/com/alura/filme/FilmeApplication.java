package br.com.alura.filme;

import br.com.alura.filme.model.DadosSerie;
import br.com.alura.filme.service.ConsumoApi;
import br.com.alura.filme.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FilmeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FilmeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t==Breaking+Bad&apikey=b1a75282");
		System.out.println(json);
		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);

	}
}
