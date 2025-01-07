package br.com.alura.filme.principal;

import br.com.alura.filme.model.*;
import br.com.alura.filme.repository.SerieRepository;
import br.com.alura.filme.service.ConsumoApi;
import br.com.alura.filme.service.ConverteDados;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private final Scanner leitura = new Scanner(System.in);
    private final ConsumoApi consumo = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=b1a75282";
    private final SerieRepository repositorio;
    private Optional<Serie> serieBusca;

    public Principal(SerieRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                     1 - Buscar séries
                     2 - Buscar Episódios
                     3 - Listar séries buscadas
                     4 - Buscar série por título
                     5 - Buscar séries por ator
                     6 - Top 5 Séries
                     7 - Buscar séries por categoria
                     8 - Filtrar séries por temporada e avaliação
                     9 - Buscar episódios por trecho
                     10 - Top 5 Episódios por série
                    11 - Buscar episodios a partir de uma data
                                        
                     0 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    listarSeriesBuscadas();
                    break;
                case 4:
                    buscarSeriePorTitulo();
                    break;
                case 5:
                    buscarSeriesPorAtor();
                    break;
                case 6:
                    buscarTop5Series();
                    break;
                case 7:
                    buscaSeriesPorCategoria();
                    break;
                case 8:
                    filtrarSeriesPorTemporadaEAvaliacao();
                    break;
                case 9:
                    buscarEpisodioPorTrecho();
                    break;
                case 10:
                    topEpisodiosPorSerie();
                    break;
                case 11:
                    buscarEpisodiosEmData();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção Inválida");
            }
        }
    }

    private void buscarEpisodiosEmData() {
        buscarSeriePorTitulo();
        if (serieBusca.isPresent()) {
            Serie serie = serieBusca.get();
            System.out.println("Digite o ano limite de lançamento:  ");
            var anoLancamentoStr = leitura.nextLine();

            try {
                int anoLancamento = Integer.parseInt(anoLancamentoStr);
                List<Episodio> episodiosAno = repositorio.episodiosPorSerieEAno(String.valueOf(anoLancamento), serie);
                episodiosAno.forEach(System.out::println);
            } catch (NumberFormatException e) {
                System.out.println("Ano de lançamento inválido. Por favor, insira um número inteiro.");
            }
        }
    }


    @Transactional
    private void topEpisodiosPorSerie() {
        buscarSeriePorTitulo();
        if (serieBusca.isPresent()) {
            Serie serie = serieBusca.get();
            List<Episodio> top5Episodios = repositorio.topEpisodiosPorSerie(serie, PageRequest.of(0, 5));
            for (Episodio e : top5Episodios) {
                System.out.printf("Série: %s Temporada %s - Episódio %s - %s - Avaliação: %.2f\n",
                        e.getSerie().getTitulo(), e.getTemporada(),
                        e.getNumeroEpisodio(), e.getTitulo(), e.getAvaliacao());
            }
        }
    }


    private void buscarEpisodioPorTrecho() {
        System.out.println("Qual nome do episódio para busca?");
        var trechoEpisodio = leitura.nextLine();
        List<Episodio> episodiosEncontrados = repositorio.buscarEpisodioPorTrecho(trechoEpisodio);
        episodiosEncontrados.forEach(e ->
                System.out.printf("Série: %s Temporada %s - Episódio %s - %s\n",
                        e.getSerie().getTitulo(), e.getTemporada(),
                        e.getNumeroEpisodio(), e.getTitulo(), e.getAvaliacao()));
    }

    private void filtrarSeriesPorTemporadaEAvaliacao() {
        System.out.println("Filtrar séries até quantas temporadas? ");
        var totalTemporadas = leitura.nextInt();
        leitura.nextLine();
        System.out.println("Com avaliação a partir de que valor? ");
        var avaliacao = leitura.nextDouble();
        leitura.nextLine();
        List<Serie> filtroSeries = repositorio.seriesPorTemporadaEAvaliacao(totalTemporadas, avaliacao);
        System.out.println("*** Séries filtradas ***");
        filtroSeries.forEach(s -> System.out.println(s.getTitulo() + "  - avaliação: " + s.getAvaliacao()));
    }

    private void buscaSeriesPorCategoria() {
        System.out.println("Deseja buscar séries de que categoria/gênero? ");
        var nomeGenero = leitura.nextLine();
        Categoria categoria = Categoria.fromPortugues(nomeGenero);
        List<Serie> seriesPorCategoria = repositorio.findByGenero(categoria);
        System.out.println("Séries da categoria " + nomeGenero);
        seriesPorCategoria.forEach(System.out::println);
    }

    private void buscarTop5Series() {
        List<Serie> serieTop = repositorio.findTop5ByOrderByAvaliacaoDesc();
        serieTop.forEach(s ->
                System.out.println(s.getTitulo() + " avaliação: " + s.getAvaliacao()));
    }

    private void buscarSeriesPorAtor() {
        System.out.println("Qual nome para busca?");
        var nomeAtor = leitura.nextLine();
        List<Serie> seriesEncontradas = repositorio.findByAtoresContainingIgnoreCase(nomeAtor);
        System.out.println("Séries em que " + nomeAtor + " trabalhou: ");
        seriesEncontradas.forEach(s ->
                System.out.println(s.getTitulo() + " avaliação: " + s.getAvaliacao()));
    }

    private void buscarSeriePorTitulo() {
        System.out.println("Escolha uma série pelo nome: ");
        var nomeSerie = leitura.nextLine();
        serieBusca = repositorio.findByTituloContainingIgnoreCase(nomeSerie);

        if (serieBusca.isPresent()) {
            System.out.println("Dados da série: " + serieBusca.get());
        } else {
            System.out.println("Série não encontrada!");
        }
    }

    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        Serie serie = new Serie(dados);
        repositorio.save(serie);
        System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        System.out.println(json);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie() {
        listarSeriesBuscadas();
        System.out.println("Escolha uma série pelo nome: ");
        var nomeSerie = leitura.nextLine();

        Optional<Serie> first = repositorio.findByTituloContainingIgnoreCase(nomeSerie);

        if (first.isPresent()) {
            var serieEncontrada = first.get();
            List<DadosTemporada> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumo.obterDados(ENDERECO + serieEncontrada.getTitulo().replace(" ", "+") + "&Season=" + i + API_KEY);
                DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
                temporadas.add(dadosTemporada);
            }
            temporadas.forEach(System.out::println);

            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream().map(e -> new Episodio(d.numero(), e)))
                    .collect(Collectors.toList());
            serieEncontrada.setEpisodios(episodios);
            repositorio.save(serieEncontrada);
        } else {
            System.out.println("Série não encontrada!");
        }
    }

    private void listarSeriesBuscadas() {
        List<Serie> series = repositorio.findAll();
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }
}
