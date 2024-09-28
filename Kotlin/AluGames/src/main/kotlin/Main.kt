package org.example

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.Scanner

// Consumindo API CheapShark
fun buscarJogo(id: String, descricao: String): Jogo? {
    val client = HttpClient.newHttpClient()
    val endereco = "https://www.cheapshark.com/api/1.0/games?id=$id"

    val request = HttpRequest.newBuilder()
        .uri(URI.create(endereco))
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())
    val json = response.body()

    // Imprime o JSON recebido para depuração
    println("Resposta da API: $json")

    return runCatching {
        // Parseia o JSON para um JsonObject
        val jsonElement = JsonParser.parseString(json)

        // Verifica se o JSON é um objeto
        if (jsonElement is JsonObject) {
            val infoObject = jsonElement.getAsJsonObject("info")

            // Define o tipo para desserializar o objeto 'info'
            val jogoInfoType = object : TypeToken<JogoInfo>() {}.type
            val jogoInfo: JogoInfo = Gson().fromJson(infoObject, jogoInfoType)

            Jogo(
                titulo = jogoInfo.title ?: "Título não disponível",
                capa = jogoInfo.thumb ?: "Capa não disponível",
                descricao = descricao
            )
        } else {
            null
        }
    }.getOrElse {
        // Em caso de exceção, exibe uma mensagem de erro e retorna null
        println("Erro ao processar a resposta da API: ${it.message}")
        null
    }
}

fun main() {
    val leia = Scanner(System.`in`)
    val jogosBuscados = mutableListOf<Jogo>() // Lista para armazenar os jogos pesquisados

    var resposta: String
    do {
        // Busca pelo ID do jogo
        println("Digite o código de um jogo para busca: ")
        val idJogo = leia.nextLine()

        val descricaoJogo = "Descrição não fornecida." // Descrição padrão se não fornecida

        val jogo = buscarJogo(idJogo, descricaoJogo)

        jogo?.let {
            val infoJogo = InfoJogo(it)
            println(infoJogo)
            jogosBuscados.add(it) // Adiciona o jogo à lista de jogos pesquisados
        } ?: println("Jogo com ID $idJogo não encontrado ou não há informações suficientes.")

        // Exibe a lista de jogos pesquisados se o usuário desejar
        println("Deseja buscar um novo ID de algum jogo ou visualizar a lista de jogos pesquisados? (S/N/L)")
        resposta = leia.nextLine()

        when (resposta.lowercase()) {
            "l" -> {
                if (jogosBuscados.isEmpty()) {
                    println("Nenhum jogo pesquisado.")
                } else {
                    println("Jogos Pesquisados:")
                    jogosBuscados.forEach { jogo ->
                        println("Título: ${jogo.titulo}, Capa: ${jogo.capa}, Descrição: ${jogo.descricao}")
                    }
                }
            }
        }

    } while (resposta.equals("s", true)) // Converte a resposta para minúsculas e compara com "s"

    println("Busca por ID de jogo finalizada com sucesso!")
}

