# Busca de Jogos com a API CheapShark

Este projeto demonstra como consumir a API CheapShark para buscar informações sobre jogos utilizando Kotlin. O código realiza a requisição HTTP, desserializa a resposta JSON e exibe as informações dos jogos para o usuário.

## Tecnologias Utilizadas

- **Kotlin**: Linguagem de programação principal.
- **Gson**: Biblioteca para manipulação e desserialização de JSON.
- **Java HTTP Client**: Para realizar requisições HTTP.
- **Scanner**: Para ler a entrada do usuário.

## Funcionalidades

- **Buscar Jogo**: Realiza uma busca na API CheapShark pelo código de um jogo e exibe as informações obtidas.
- **Armazenar Jogos**: Adiciona os jogos pesquisados a uma lista para visualização posterior.
- **Visualização**: Permite ao usuário visualizar a lista de jogos pesquisados ou buscar novos jogos.

## Como Usar

1. **Configuração do Ambiente**: Certifique-se de que o projeto está configurado para utilizar Kotlin e as bibliotecas necessárias (Gson e Java HTTP Client).

2. **Executar o Código**:
    - Compile e execute o código Kotlin.
    - O programa solicitará que você insira o código de um jogo para busca.
    - Após a busca, o programa exibirá as informações do jogo, se encontrado.
    - Você pode buscar novos jogos ou visualizar a lista de jogos pesquisados.
    - A busca termina quando você optar por não buscar novos jogos.

## Código

```kotlin
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
```
# Classes
`JogoInfo`

A classe `JogoInfo` é uma representação das informações básicas de um jogo, conforme obtidas de uma API. Ela é projetada para armazenar e manipular dados relacionados a jogos.

## Estrutura da Classe

A classe `JogoInfo` é definida da seguinte forma:

```kotlin
data class JogoInfo(
    val title: String?,
    val steamAppID: String?,
    val thumb: String?
)

```

# Classe `Jogo`

A classe `Jogo` é uma representação de um jogo, contendo informações básicas como título, capa e descrição. Ela é projetada para armazenar e exibir detalhes sobre um jogo.

## Estrutura da Classe

A classe `Jogo` é definida da seguinte forma:

```kotlin
class Jogo(
    val titulo: String,
    val capa: String,
    val descricao: String
) {
    override fun toString(): String {
        return "Meu Jogo:\n" +
                "Titulo: $titulo\n" +
                "Capa: $capa\n" +
                "Descricao: $descricao"
    }
}
```

# Saída do Console

## Jogos Pesquisados

1. **Título:** Max Payne 2  
   **Capa:** ![Max Payne 2](https://www.wingamestore.com/images_boxshots/master/max-payne-2-1556323252.jpg)  
   **Descrição:** Descrição não fornecida.

2. **Título:** Grand Theft Auto: Vice City - The Definitive Edition  
   **Capa:** ![Grand Theft Auto: Vice City - The Definitive Edition](https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/1546990/capsule_sm_120.jpg?t=1676922450)  
   **Descrição:** Descrição não fornecida.

3. **Título:** Far Cry 3  
   **Capa:** ![Far Cry 3](https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/220240/capsule_sm_120.jpg?t=1682960396)  
   **Descrição:** Descrição não fornecida.

4. **Título:** Crash Bandicoot N. Sane Trilogy  
   **Capa:** ![Crash Bandicoot N. Sane Trilogy](https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/731490/capsule_sm_120.jpg?t=1568841571)  
   **Descrição:** Descrição não fornecida.

5. **Título:** Hitman: Blood Money  
   **Capa:** ![Hitman: Blood Money](https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/6860/capsule_sm_120.jpg?t=1667311083)  
   **Descrição:** Descrição não fornecida.

6. **Título:** The Forest  
   **Capa:** ![The Forest](https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/242760/capsule_sm_120.jpg?t=1699381053)  
   **Descrição:** Descrição não fornecida.

Busca por ID de jogo finalizada com sucesso!

