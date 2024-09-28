import getDados from "./getDados.js";

// Mapeia os elementos DOM que você deseja atualizar
const elementos = {
    top5: document.querySelector('[data-name="top5"]'),
    lancamentos: document.querySelector('[data-name="lancamentos"]'),
    series: document.querySelector('[data-name="series"]')
};

// Função para criar a lista de filmes
function criarListaFilmes(elemento, dados) {
    const ulExistente = elemento.querySelector('ul');

    // Remove um elemento <ul> existente, se houver
    if (ulExistente) {
        elemento.removeChild(ulExistente);
    }

    const ul = document.createElement('ul');
    ul.className = 'lista';

    const listaHTML = dados.map((filme) => `
        <li>
            <a href="/detalhes.html?id=${filme.id}">
                <img src="${filme.poster}" alt="${filme.titulo}">
            </a>
        </li>
    `).join('');

    ul.innerHTML = listaHTML;
    elemento.appendChild(ul);
}

// Função genérica para tratamento de erros
function lidarComErro(mensagemErro) {
    console.error(mensagemErro);
}

// Manipulação da seleção de categoria
const categoriaSelect = document.querySelector('[data-categorias]');
const sectionsParaOcultar = document.querySelectorAll('.section');

categoriaSelect.addEventListener('change', function () {
    const categoria = document.querySelector('[data-name="categoria"]');
    const categoriaSelecionada = categoriaSelect.value;

    if (categoriaSelecionada === 'todos') {
        sectionsParaOcultar.forEach(section => section.classList.remove('hidden'));
        categoria.classList.add('hidden');
    } else {
        sectionsParaOcultar.forEach(section => section.classList.add('hidden'));
        categoria.classList.remove('hidden');

        // Faz uma solicitação para o endpoint com a categoria selecionada
        getDados(`/series/categoria/${categoriaSelecionada}`)
            .then(data => {
                criarListaFilmes(categoria, data);
            })
            .catch(error => {
                lidarComErro("Ocorreu um erro ao carregar os dados da categoria.");
            });
    }
});

// Função para gerar séries
geraSeries();
function geraSeries() {
    const urls = ['/series/top5', '/series/lancamentos', '/series'];

    // Faz todas as solicitações em paralelo
    Promise.all(urls.map(url => getDados(url)))
        .then(data => {
            criarListaFilmes(elementos.top5, data[0]);
            criarListaFilmes(elementos.lancamentos, data[1]);
            criarListaFilmes(elementos.series, data[2].slice(0, 5));
        })
        .catch(error => {
            lidarComErro("Ocorreu um erro ao carregar os dados.");
        });

        
}
