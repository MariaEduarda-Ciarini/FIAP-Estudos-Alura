package br.com.alura.filme.controller;

import br.com.alura.filme.dto.EpisodioDTO;
import br.com.alura.filme.dto.SerieDTO;
import br.com.alura.filme.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SeriesService servico;

    @GetMapping
    public List<SerieDTO> obterSeries() {
        return servico.obterTodasAsSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obterTop5Series() {
        return servico.obterTop5Series();
    }

    @GetMapping("/lancamentos")
    public List<SerieDTO> obterLancamentos() {
        return servico.obterLancamentos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SerieDTO> obterPorId(@PathVariable Long id) {
        try {
            SerieDTO serie = servico.obterPorId(id);
            return ResponseEntity.ok(serie);
        } catch (SerieNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 se não encontrar
        }
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> obterTodasTemporadas(@PathVariable Long id) {
        return servico.obterTodasTemporadas(id);
    }

    @GetMapping("/{id}/temporadas/{numero}")
    public List<EpisodioDTO> obterTemporadasPorNumero(@PathVariable Long id, @PathVariable Long numero) {
        return servico.obterTemporadasPorNumero(id, numero);
    }

    @GetMapping("/categoria/{nomeGenero}")
    public List<SerieDTO> obterSeriesPorCategoria(@PathVariable String nomeGenero){
        return servico.obterSeriesPorCategoria(nomeGenero);
    }
}
