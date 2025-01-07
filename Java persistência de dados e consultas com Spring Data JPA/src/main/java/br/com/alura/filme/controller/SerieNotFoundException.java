package br.com.alura.filme.controller;

public class SerieNotFoundException extends RuntimeException {
    public SerieNotFoundException(Long id) {
        super("Série não encontrada com o ID: " + id);
    }
}