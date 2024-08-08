package br.com.alura.filme.model;

public enum Categoria {

    ACAO("Action", "Ação"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Cómedia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime"),
    HORROR("Horror", "Horror"),
    ANIMACAO("Animation", "Animação");

    private final String categoriaOmdb;

    private String categoriaPortugues;

    Categoria(String categoriaOmdb, String categoriaPortugues) {
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaPortugues = categoriaPortugues;
    }

    public static Categoria fromString(String text) throws IllegalArgumentException {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }

        throw new IllegalArgumentException("Nenhuma categoria encontrada para a String fornecida: " + text);
    }

    public static Categoria fromPortugues(String text) throws IllegalArgumentException {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaPortugues.equalsIgnoreCase(text)) {
                return categoria;
            }
        }

        throw new IllegalArgumentException("Nenhuma categoria encontrada para a String fornecida: " + text);
    }
}
