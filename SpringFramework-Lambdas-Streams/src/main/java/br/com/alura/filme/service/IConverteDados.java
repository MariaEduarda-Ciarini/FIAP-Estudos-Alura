package br.com.alura.filme.service;

public interface IConverteDados {
     <T> T  obterDados(String json, Class<T> classe);
}
