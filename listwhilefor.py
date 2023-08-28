equipamentos = []
valores = []
seriais = []
departamentos = []

resposta = "S"
while resposta == "S":
    equipamentos.append(input("Equipamento: "))
    valores.append(float(input("Valor: ")))
    seriais.append(int(input("Numero Serial: ")))
    departamentos.append(input("Departamento: "))
    resposta = input("Digite \"s\" para continuar: ").upper()

#  for equipamento in equipamentos:
#  print("Equipamento: ", equipamento)

for indice in range(0, len(equipamentos)):
    print("\nEquipamento...:", (indice + 1))
    print("Nome.......:", equipamentos[indice])
    print("Valor.......:", valores[indice])
    print("Serial.......:", seriais[indice])
    print("Departamento.......:", departamentos[indice])

busca = input("\nDigite o nome do equipamento que deseja buscar: ")
for indice in range(0, len(equipamentos)):
    if busca == equipamentos[indice]:
        print("Valor...:", valores[indice])
        print("Serial.:", seriais[indice])


depreciacao = input("\nDigite o nome do equipamento que será depreciado: ")
for indice in range(0, len(equipamentos)):
    if depreciacao == equipamentos[indice]:
        print("Valor antigo: ", valores[indice])
        valores[indice] = valores[indice] * 0.9
        print("Novo valor: ", valores[indice])

