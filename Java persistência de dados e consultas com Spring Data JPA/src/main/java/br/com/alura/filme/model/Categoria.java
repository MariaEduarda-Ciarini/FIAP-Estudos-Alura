package br.com.alura.filme.model;

public enum Categoria {

    ACAO("Action"),

    ROMANCE("Romance"),

    COMEDIA("Comedy"),

    DRAMA("Drame"),

    CRIME("Crime");

    private String categoriaOmdb;

    Categoria(String categoriaOmdb) {
        this.categoriaOmdb = categoriaOmdb;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }

        throw new IllegalArgumentException("Nenhuma categoria encontrada para a String fornecida: ");
    }

}
