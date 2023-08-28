usuarios = {}
print(usuarios)

usuarios = {"Gil Kane": ["Lendas do Homem de aço", "20/03/18", "Local_02"],
            "Archi Goodwin": ["Lendas do cavaleiro das Trevas", "21/03/18", "Depart_01"]}

print(usuarios)

usuarios["Geoge"] = ["Geoge Pérez", "30/03/18", "Depart_02"]
print(usuarios)

print("####----####")
print(usuarios.get("Archi Goodwin"))
