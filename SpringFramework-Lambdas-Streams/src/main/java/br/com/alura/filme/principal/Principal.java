package br.com.alura.filme.principal;
import br.com.alura.filme.model.DadosEpisodio;
import br.com.alura.filme.model.DadosSerie;
import br.com.alura.filme.model.DadosTemporada;
import br.com.alura.filme.service.ConsumoApi;
import br.com.alura.filme.service.ConverteDados;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Principal {

    private Scanner leitura = new Scanner(System.in);

    private ConsumoApi consumo = new ConsumoApi();

    private ConverteDados conversor = new ConverteDados();

    private  final String ENDERECO = "https://www.omdbapi.com/?t=";
    private  final String API_KEY = "&apikey=b1a75282";

    public void exibeMenu(){
        System.out.println("Digite o nome da série para busca: ");
        var nomesSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomesSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i<= dados.totalTemporadas(); i++){
			json = consumo.obterDados(ENDERECO + nomesSerie.replace(" ", "+") +"&season=" +i+ API_KEY);
		DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);

		}

		temporadas.forEach(System.out::println);

//        for (int i = 0; i< dados.totalTemporadas(); i ++ ) {
//            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
//            for(int j = 0; j< episodiosTemporada.size(); j ++){
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }
//        }

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
    }
}
