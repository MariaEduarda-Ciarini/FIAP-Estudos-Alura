package br.com.alura.filme;

import br.com.alura.filme.principal.Principal;
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
		Principal principal = new Principal();
		principal.exibeMenu();


//		var consumoApi = new ConsumoApi();
//		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=Stranger+Things&y=2016&apikey=b1a75282");
//		System.out.println(json);
//		ConverteDados conversor = new ConverteDados();
//		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
//		System.out.println(dados);
//		json = consumoApi.obterDados("https://www.omdbapi.com/?t=Stranger+Things&season=3&episode=2&apikey=b1a75282");
//		DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
//		System.out.println(dadosEpisodio);

//		List<DadosTemporada> temporadas = new ArrayList<>();

//		for (int i = 1; i<= dados.totalTemporadas(); i++){
//			json = consumoApi.obterDados("https://www.omdbapi.com/?t=Stranger+Things&season="+ i +"&apikey=b1a75282");
//		DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
//			temporadas.add(dadosTemporada);
//		}
//		temporadas.forEach(System.out::println);
	}
}
