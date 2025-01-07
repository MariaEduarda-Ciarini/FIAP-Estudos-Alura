from flask import Flask, render_template

class   Jogo:
    def __init__(self, nome, categoria, console):
        self.nome = nome
        self.categoria = categoria
        self.console = console


app = Flask(__name__)
@app.route('/inicio')
def ola():
    jogo1 =Jogo('Max Payne 2', 'Detetive', 'PC, XBOX360, PS2')
    jogo2 =Jogo('Bully', 'Aventura', 'PS2, XBOX, PC, NINTENDO')
    jogo3 =Jogo('Skate 3', 'Esportivo', 'XBOX')

    lista = [jogo1, jogo2, jogo3]
    return render_template('lista.html', titulo= 'Jogos', jogos=lista)

app.run()