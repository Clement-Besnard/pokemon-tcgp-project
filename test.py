import requests
import random

# URL de l'API
API_URL = "http://localhost:8080/pokemons"

# Liste des 151 premiers Pokémon du Pokédex
POKEMONS = [
    {"nom": "Bulbizarre", "type": "PLANTE", "rarity": 1, "attack1": "Fouet Lianes", "attack2": "Tranch'Herbe"},
    {"nom": "Herbizarre", "type": "PLANTE", "rarity": 2, "attack1": "Fouet Lianes", "attack2": "Tranch'Herbe"},
    {"nom": "Florizarre", "type": "PLANTE", "rarity": 4, "attack1": "Fouet Lianes", "attack2": "Tranch'Herbe"},
    {"nom": "Salamèche", "type": "FEU", "rarity": 1, "attack1": "Flammèche", "attack2": "Griffe"},
    {"nom": "Reptincel", "type": "FEU", "rarity": 2, "attack1": "Flammèche", "attack2": "Griffe"},
    {"nom": "Dracaufeu", "type": "FEU", "rarity": 5, "attack1": "Lance-Flammes", "attack2": "Griffe"},
    {"nom": "Carapuce", "type": "EAU", "rarity": 1, "attack1": "Pistolet à O", "attack2": "Morsure"},
    {"nom": "Carabaffe", "type": "EAU", "rarity": 2, "attack1": "Pistolet à O", "attack2": "Morsure"},
    {"nom": "Tortank", "type": "EAU", "rarity": 4, "attack1": "Hydrocanon", "attack2": "Morsure"},
    {"nom": "Chenipan", "type": "INSECTE", "rarity": 1, "attack1": "Charge", "attack2": "Sécrétion"},
    {"nom": "Chrysacier", "type": "INSECTE", "rarity": 1, "attack1": "Armure", "attack2": "Sécrétion"},
    {"nom": "Papilusion", "type": "INSECTE", "rarity": 2, "attack1": "Poudre Dodo", "attack2": "Vent Argenté"},
    {"nom": "Aspicot", "type": "INSECTE", "rarity": 1, "attack1": "Dard-Venin", "attack2": "Sécrétion"},
    {"nom": "Coconfort", "type": "INSECTE", "rarity": 1, "attack1": "Armure", "attack2": "Sécrétion"},
    {"nom": "Dardargnan", "type": "INSECTE", "rarity": 2, "attack1": "Dard-Nuée", "attack2": "Dard-Venin"},
    {"nom": "Roucool", "type": "VOL", "rarity": 1, "attack1": "Tornade", "attack2": "Cru-Aile"},
    {"nom": "Roucoups", "type": "VOL", "rarity": 2, "attack1": "Tornade", "attack2": "Cru-Aile"},
    {"nom": "Roucarnage", "type": "VOL", "rarity": 3, "attack1": "Tornade", "attack2": "Cru-Aile"},
    {"nom": "Rattata", "type": "NORMAL", "rarity": 1, "attack1": "Charge", "attack2": "Morsure"},
    {"nom": "Rattatac", "type": "NORMAL", "rarity": 2, "attack1": "Charge", "attack2": "Morsure"},
    {"nom": "Piafabec", "type": "VOL", "rarity": 1, "attack1": "Cru-Aile", "attack2": "Picpic"},
    {"nom": "Rapasdepic", "type": "VOL", "rarity": 2, "attack1": "Cru-Aile", "attack2": "Picpic"},
    {"nom": "Abo", "type": "POISON", "rarity": 1, "attack1": "Dard-Venin", "attack2": "Morsure"},
    {"nom": "Arbok", "type": "POISON", "rarity": 2, "attack1": "Dard-Venin", "attack2": "Morsure"},
    {"nom": "Pikachu", "type": "ELECTRIQUE", "rarity": 2, "attack1": "Éclair", "attack2": "Tonnerre"},
    {"nom": "Raichu", "type": "ELECTRIQUE", "rarity": 3, "attack1": "Éclair", "attack2": "Tonnerre"},
    {"nom": "Sabelette", "type": "SOL", "rarity": 1, "attack1": "Griffe", "attack2": "Jet de Sable"},
    {"nom": "Sablaireau", "type": "SOL", "rarity": 2, "attack1": "Griffe", "attack2": "Jet de Sable"},
    {"nom": "Nidoran♀", "type": "POISON", "rarity": 1, "attack1": "Dard-Venin", "attack2": "Griffe"},
    {"nom": "Nidorina", "type": "POISON", "rarity": 2, "attack1": "Dard-Venin", "attack2": "Griffe"},
    {"nom": "Nidoqueen", "type": "POISON", "rarity": 4, "attack1": "Dard-Venin", "attack2": "Griffe"},
    {"nom": "Nidoran♂", "type": "POISON", "rarity": 1, "attack1": "Dard-Venin", "attack2": "Griffe"},
    {"nom": "Nidorino", "type": "POISON", "rarity": 2, "attack1": "Dard-Venin", "attack2": "Griffe"},
    {"nom": "Nidoking", "type": "POISON", "rarity": 4, "attack1": "Dard-Venin", "attack2": "Griffe"},
    {"nom": "Mélofée", "type": "FEE", "rarity": 2, "attack1": "Métronome", "attack2": "Écras'Face"},
    {"nom": "Mélodelfe", "type": "FEE", "rarity": 3, "attack1": "Métronome", "attack2": "Écras'Face"},
    {"nom": "Goupix", "type": "FEU", "rarity": 1, "attack1": "Flammèche", "attack2": "Griffe"},
    {"nom": "Feunard", "type": "FEU", "rarity": 3, "attack1": "Flammèche", "attack2": "Griffe"},
    {"nom": "Rondoudou", "type": "NORMAL", "rarity": 1, "attack1": "Écras'Face", "attack2": "Berceuse"},
    {"nom": "Grodoudou", "type": "NORMAL", "rarity": 2, "attack1": "Écras'Face", "attack2": "Berceuse"},
    {"nom": "Nosferapti", "type": "POISON", "rarity": 1, "attack1": "Cru-Aile", "attack2": "Morsure"},
    {"nom": "Nosferalto", "type": "POISON", "rarity": 2, "attack1": "Cru-Aile", "attack2": "Morsure"},
    {"nom": "Mystherbe", "type": "PLANTE", "rarity": 1, "attack1": "Tranch'Herbe", "attack2": "Poudre Dodo"},
    {"nom": "Ortide", "type": "PLANTE", "rarity": 2, "attack1": "Tranch'Herbe", "attack2": "Poudre Dodo"},
    {"nom": "Rafflesia", "type": "PLANTE", "rarity": 3, "attack1": "Tranch'Herbe", "attack2": "Poudre Dodo"},
    {"nom": "Paras", "type": "INSECTE", "rarity": 1, "attack1": "Tranch'Herbe", "attack2": "Griffe"},
    {"nom": "Parasect", "type": "INSECTE", "rarity": 2, "attack1": "Tranch'Herbe", "attack2": "Griffe"},
    {"nom": "Mimitoss", "type": "INSECTE", "rarity": 1, "attack1": "Psyko", "attack2": "Poudre Dodo"},
    {"nom": "Aéromite", "type": "INSECTE", "rarity": 2, "attack1": "Psyko", "attack2": "Poudre Dodo"},
    {"nom": "Taupiqueur", "type": "SOL", "rarity": 1, "attack1": "Griffe", "attack2": "Jet de Sable"},
    {"nom": "Triopikeur", "type": "SOL", "rarity": 2, "attack1": "Griffe", "attack2": "Jet de Sable"},
    {"nom": "Miaouss", "type": "NORMAL", "rarity": 1, "attack1": "Griffe", "attack2": "Morsure"},
    {"nom": "Persian", "type": "NORMAL", "rarity": 2, "attack1": "Griffe", "attack2": "Morsure"},
    {"nom": "Psykokwak", "type": "EAU", "rarity": 1, "attack1": "Pistolet à O", "attack2": "Morsure"},
    {"nom": "Akwakwak", "type": "EAU", "rarity": 2, "attack1": "Pistolet à O", "attack2": "Morsure"},
    {"nom": "Férosinge", "type": "COMBAT", "rarity": 1, "attack1": "Poing-Karaté", "attack2": "Morsure"},
    {"nom": "Colossinge", "type": "COMBAT", "rarity": 2, "attack1": "Poing-Karaté", "attack2": "Morsure"},
    {"nom": "Caninos", "type": "FEU", "rarity": 2, "attack1": "Flammèche", "attack2": "Morsure"},
    {"nom": "Arcanin", "type": "FEU", "rarity": 4, "attack1": "Flammèche", "attack2": "Morsure"},
    {"nom": "Ptitard", "type": "EAU", "rarity": 1, "attack1": "Pistolet à O", "attack2": "Morsure"},
    {"nom": "Têtarte", "type": "EAU", "rarity": 2, "attack1": "Pistolet à O", "attack2": "Morsure"},
    {"nom": "Tartard", "type": "EAU", "rarity": 3, "attack1": "Hydrocanon", "attack2": "Morsure"},
    {"nom": "Abra", "type": "PSY", "rarity": 1, "attack1": "Choc Mental", "attack2": "Téléport"},
    {"nom": "Kadabra", "type": "PSY", "rarity": 2, "attack1": "Choc Mental", "attack2": "Téléport"},
    {"nom": "Alakazam", "type": "PSY", "rarity": 4, "attack1": "Choc Mental", "attack2": "Téléport"},
    {"nom": "Machoc", "type": "COMBAT", "rarity": 1, "attack1": "Poing-Karaté", "attack2": "Morsure"},
    {"nom": "Machopeur", "type": "COMBAT", "rarity": 2, "attack1": "Poing-Karaté", "attack2": "Morsure"},
    {"nom": "Mackogneur", "type": "COMBAT", "rarity": 3, "attack1": "Poing-Karaté", "attack2": "Morsure"},
    {"nom": "Chétiflor", "type": "PLANTE", "rarity": 1, "attack1": "Tranch'Herbe", "attack2": "Fouet Lianes"},
    {"nom": "Boustiflor", "type": "PLANTE", "rarity": 2, "attack1": "Tranch'Herbe", "attack2": "Fouet Lianes"},
    {"nom": "Empiflor", "type": "PLANTE", "rarity": 3, "attack1": "Tranch'Herbe", "attack2": "Fouet Lianes"},
    {"nom": "Tentacool", "type": "EAU", "rarity": 1, "attack1": "Pistolet à O", "attack2": "Morsure"},
    {"nom": "Tentacruel", "type": "EAU", "rarity": 3, "attack1": "Hydrocanon", "attack2": "Morsure"},
    {"nom": "Racaillou", "type": "ROCHE", "rarity": 1, "attack1": "Jet-Pierres", "attack2": "Morsure"},
    {"nom": "Gravalanch", "type": "ROCHE", "rarity": 2, "attack1": "Jet-Pierres", "attack2": "Morsure"},
    {"nom": "Grolem", "type": "ROCHE", "rarity": 3, "attack1": "Jet-Pierres", "attack2": "Morsure"},
    {"nom": "Ponyta", "type": "FEU", "rarity": 2, "attack1": "Flammèche", "attack2": "Morsure"},
    {"nom": "Galopa", "type": "FEU", "rarity": 3, "attack1": "Flammèche", "attack2": "Morsure"},
    {"nom": "Ramoloss", "type": "EAU", "rarity": 1, "attack1": "Pistolet à O", "attack2": "Morsure"},
    {"nom": "Flagadoss", "type": "EAU", "rarity": 3, "attack1": "Hydrocanon", "attack2": "Morsure"},
    {"nom": "Magnéti", "type": "ELECTRIQUE", "rarity": 1, "attack1": "Éclair", "attack2": "Tonnerre"},
    {"nom": "Magnéton", "type": "ELECTRIQUE", "rarity": 2, "attack1": "Éclair", "attack2": "Tonnerre"},
    {"nom": "Canarticho", "type": "VOL", "rarity": 2, "attack1": "Cru-Aile", "attack2": "Morsure"},
    {"nom": "Doduo", "type": "VOL", "rarity": 1, "attack1": "Cru-Aile", "attack2": "Morsure"},
    {"nom": "Dodrio", "type": "VOL", "rarity": 2, "attack1": "Cru-Aile", "attack2": "Morsure"},
    {"nom": "Otaria", "type": "EAU", "rarity": 1, "attack1": "Pistolet à O", "attack2": "Morsure"},
    {"nom": "Lamantine", "type": "EAU", "rarity": 2, "attack1": "Pistolet à O", "attack2": "Morsure"},
    {"nom": "Tadmorv", "type": "POISON", "rarity": 1, "attack1": "Dard-Venin", "attack2": "Morsure"},
    {"nom": "Grotadmorv", "type": "POISON", "rarity": 2, "attack1": "Dard-Venin", "attack2": "Morsure"},
    {"nom": "Kokiyas", "type": "EAU", "rarity": 1, "attack1": "Pistolet à O", "attack2": "Morsure"},
    {"nom": "Crustabri", "type": "EAU", "rarity": 3, "attack1": "Hydrocanon", "attack2": "Morsure"},
    {"nom": "Fantominus", "type": "SPECTRE", "rarity": 1, "attack1": "Léchouille", "attack2": "Morsure"},
    {"nom": "Spectrum", "type": "SPECTRE", "rarity": 2, "attack1": "Léchouille", "attack2": "Morsure"},
    {"nom": "Ectoplasma", "type": "SPECTRE", "rarity": 4, "attack1": "Léchouille", "attack2": "Morsure"},
    {"nom": "Onix", "type": "ROCHE", "rarity": 2, "attack1": "Jet-Pierres", "attack2": "Éboulement"},
    {"nom": "Soporifik", "type": "PSY", "rarity": 1, "attack1": "Choc Mental", "attack2": "Hypnose"},
    {"nom": "Hypnomade", "type": "PSY", "rarity": 2, "attack1": "Choc Mental", "attack2": "Hypnose"},
    {"nom": "Krabby", "type": "EAU", "rarity": 1, "attack1": "Pince-Masse", "attack2": "Bulles d'O"},
    {"nom": "Krabboss", "type": "EAU", "rarity": 2, "attack1": "Pince-Masse", "attack2": "Bulles d'O"},
    {"nom": "Voltorbe", "type": "ELECTRIQUE", "rarity": 1, "attack1": "Éclair", "attack2": "Tonnerre"},
    {"nom": "Électrode", "type": "ELECTRIQUE", "rarity": 2, "attack1": "Éclair", "attack2": "Tonnerre"},
    {"nom": "Noeunoeuf", "type": "PLANTE", "rarity": 1, "attack1": "Choc Mental", "attack2": "Bomb-Beurk"},
    {"nom": "Noadkoko", "type": "PLANTE", "rarity": 3, "attack1": "Choc Mental", "attack2": "Bomb-Beurk"},
    {"nom": "Osselait", "type": "SOL", "rarity": 1, "attack1": "Massd'Os", "attack2": "Osmerang"},
    {"nom": "Ossatueur", "type": "SOL", "rarity": 2, "attack1": "Massd'Os", "attack2": "Osmerang"},
    {"nom": "Kicklee", "type": "COMBAT", "rarity": 3, "attack1": "Pied Voltige", "attack2": "Mawashi Geri"},
    {"nom": "Tygnon", "type": "COMBAT", "rarity": 3, "attack1": "Poing-Karaté", "attack2": "Météores"},
    {"nom": "Excelangue", "type": "NORMAL", "rarity": 2, "attack1": "Léchouille", "attack2": "Morsure"},
    {"nom": "Smogo", "type": "POISON", "rarity": 1, "attack1": "Gaz Toxik", "attack2": "Explosion"},
    {"nom": "Smogogo", "type": "POISON", "rarity": 2, "attack1": "Gaz Toxik", "attack2": "Explosion"},
    {"nom": "Rhinocorne", "type": "SOL", "rarity": 1, "attack1": "Charge", "attack2": "Écrasement"},
    {"nom": "Rhinoféros", "type": "SOL", "rarity": 3, "attack1": "Charge", "attack2": "Écrasement"},
    {"nom": "Leveinard", "type": "NORMAL", "rarity": 4, "attack1": "Écras'Face", "attack2": "Météores"},
    {"nom": "Saquedeneu", "type": "PLANTE", "rarity": 2, "attack1": "Fouet Lianes", "attack2": "Tranch'Herbe"},
    {"nom": "Kangourex", "type": "NORMAL", "rarity": 3, "attack1": "Morsure", "attack2": "Météores"},
    {"nom": "Hypotrempe", "type": "EAU", "rarity": 1, "attack1": "Pistolet à O", "attack2": "Bulles d'O"},
    {"nom": "Hypocéan", "type": "EAU", "rarity": 2, "attack1": "Pistolet à O", "attack2": "Bulles d'O"},
    {"nom": "Poissirène", "type": "EAU", "rarity": 1, "attack1": "Picpic", "attack2": "Hydrocanon"},
    {"nom": "Poissoroy", "type": "EAU", "rarity": 2, "attack1": "Picpic", "attack2": "Hydrocanon"},
    {"nom": "Stari", "type": "EAU", "rarity": 1, "attack1": "Pistolet à O", "attack2": "Bulles d'O"},
    {"nom": "Staross", "type": "EAU", "rarity": 3, "attack1": "Pistolet à O", "attack2": "Bulles d'O"},
    {"nom": "M. Mime", "type": "PSY", "rarity": 3, "attack1": "Choc Mental", "attack2": "Hypnose"},
    {"nom": "Insécateur", "type": "INSECTE", "rarity": 3, "attack1": "Tranch'Herbe", "attack2": "Cru-Aile"},
    {"nom": "Lippoutou", "type": "GLACE", "rarity": 3, "attack1": "Blizzard", "attack2": "Poudreuse"},
    {"nom": "Élektek", "type": "ELECTRIQUE", "rarity": 3, "attack1": "Éclair", "attack2": "Tonnerre"},
    {"nom": "Magmar", "type": "FEU", "rarity": 3, "attack1": "Lance-Flammes", "attack2": "Poing de Feu"},
    {"nom": "Scarabrute", "type": "INSECTE", "rarity": 3, "attack1": "Tranch'Herbe", "attack2": "Cru-Aile"},
    {"nom": "Tauros", "type": "NORMAL", "rarity": 3, "attack1": "Charge", "attack2": "Écrasement"},
    {"nom": "Magicarpe", "type": "EAU", "rarity": 1, "attack1": "Trempette", "attack2": "Charge"},
    {"nom": "Léviator", "type": "EAU", "rarity": 4, "attack1": "Hydrocanon", "attack2": "Morsure"},
    {"nom": "Lokhlass", "type": "EAU", "rarity": 4, "attack1": "Hydrocanon", "attack2": "Blizzard"},
    {"nom": "Métamorph", "type": "NORMAL", "rarity": 3, "attack1": "Morphing", "attack2": "Écrasement"},
    {"nom": "Évoli", "type": "NORMAL", "rarity": 2, "attack1": "Morsure", "attack2": "Météores"},
    {"nom": "Aquali", "type": "EAU", "rarity": 3, "attack1": "Hydrocanon", "attack2": "Morsure"},
    {"nom": "Voltali", "type": "ELECTRIQUE", "rarity": 3, "attack1": "Éclair", "attack2": "Tonnerre"},
    {"nom": "Pyroli", "type": "FEU", "rarity": 3, "attack1": "Lance-Flammes", "attack2": "Poing de Feu"},
    {"nom": "Porygon", "type": "NORMAL", "rarity": 3, "attack1": "Charge", "attack2": "Écrasement"},
    {"nom": "Amonita", "type": "ROCHE", "rarity": 2, "attack1": "Jet-Pierres", "attack2": "Éboulement"},
    {"nom": "Amonistar", "type": "ROCHE", "rarity": 3, "attack1": "Jet-Pierres", "attack2": "Éboulement"},
    {"nom": "Kabuto", "type": "ROCHE", "rarity": 2, "attack1": "Jet-Pierres", "attack2": "Éboulement"},
    {"nom": "Kabutops", "type": "ROCHE", "rarity": 3, "attack1": "Jet-Pierres", "attack2": "Éboulement"},
    {"nom": "Ptéra", "type": "ROCHE", "rarity": 4, "attack1": "Jet-Pierres", "attack2": "Éboulement"},
    {"nom": "Ronflex", "type": "NORMAL", "rarity": 4, "attack1": "Écras'Face", "attack2": "Météores"},
    {"nom": "Artikodin", "type": "GLACE", "rarity": 5, "attack1": "Blizzard", "attack2": "Poudreuse"},
    {"nom": "Électhor", "type": "ELECTRIQUE", "rarity": 5, "attack1": "Éclair", "attack2": "Tonnerre"},
    {"nom": "Sulfura", "type": "FEU", "rarity": 5, "attack1": "Lance-Flammes", "attack2": "Poing de Feu"},
    {"nom": "Minidraco", "type": "DRAGON", "rarity": 2, "attack1": "Draco-Rage", "attack2": "Hydrocanon"},
    {"nom": "Draco", "type": "DRAGON", "rarity": 3, "attack1": "Draco-Rage", "attack2": "Hydrocanon"},
    {"nom": "Dracolosse", "type": "DRAGON", "rarity": 5, "attack1": "Draco-Rage", "attack2": "Hydrocanon"},
    {"nom": "Mewtwo", "type": "PSY", "rarity": 5, "attack1": "Choc Mental", "attack2": "Hypnose"},
    {"nom": "Mew", "type": "PSY", "rarity": 5, "attack1": "Choc Mental", "attack2": "Hypnose"}
]

# Clé d'authentification
HEADERS = {
    "x-api-key": "ProjectToken",
    "Content-Type": "application/json"
}


# Boucle pour créer les Pokémon
for pokemon in POKEMONS:
    response = requests.post(API_URL, json=pokemon, headers=HEADERS)

    if response.status_code == 201:
        print(f"✅ Créé Pokémon : {pokemon['nom']}, Type: {pokemon['type']}, Rarity: {pokemon['rarity']}")
    else:
        print(f"❌ Erreur lors de la création de {pokemon['nom']}: {response.status_code} - {response.text}")

response = requests.post("http://localhost:8080/dresseurs", json={"nom":"Clément", "prenom":"Besnard"}, headers=HEADERS)
if response.status_code == 201:
    print(f"✅ Créé Dresseur : Clément Besnard")
else:
    print(f"❌ Erreur lors de la création de Clément Besnard: {response.status_code} - {response.text}")
response = requests.post("http://localhost:8080/dresseurs", json={"nom":"Mathieu", "prenom":"Crespin"}, headers=HEADERS)
if response.status_code == 201:
    print(f"✅ Créé Dresseur : Mathieu Crespin")
else:
    print(f"❌ Erreur lors de la création de Mathieu Crespin : {response.status_code} - {response.text}")
response = requests.post("http://localhost:8080/dresseurs", json={"nom":"Gaël", "prenom":"Djebar"}, headers=HEADERS)
if response.status_code == 201:
    print(f"✅ Créé Dresseur : Gaël Djebar")
else:
    print(f"❌ Erreur lors de la création de Gaël Djebar: {response.status_code} - {response.text}")

response = requests.get("http://localhost:8080/dresseurs", headers=HEADERS)
if response.status_code == 200:
    dresseur1 = response.json()[0]["uuid"]
    dresseur2 = response.json()[1]["uuid"]
    dresseur3 = response.json()[2]["uuid"]
    print(f"✅ Clément Besnard a pour uuid {dresseur1}, Mathieu Crespin a pour uuid {dresseur2} et Gaël Djebar a pour uuid {dresseur3}")
else:
    print(f"❌ Erreur lors de l'obtention des dresseurs : {response.status_code} - {response.text}")

response = requests.post(f"http://localhost:8080/dresseurs/{dresseur1}/ouvrir-paquet", headers=HEADERS)
if response.status_code == 201:
    print(f"✅ Ouverture du paquet pour Clément Besnard")
else:
    print(f"❌ Erreur lors de l'ouverture du paquet pour Clément Besnard : {response.status_code} - {response.text}")

response = requests.post(f"http://localhost:8080/dresseurs/{dresseur2}/ouvrir-paquet", headers=HEADERS)
if response.status_code == 201:
    print(f"✅ Ouverture du paquet pour Mathieu Crespin")
else:
    print(f"❌ Erreur lors de l'ouverture du paquet pour Mathieu Crespin : {response.status_code} - {response.text}")

response = requests.post(f"http://localhost:8080/dresseurs/{dresseur3}/ouvrir-paquet", headers=HEADERS)
if response.status_code == 201:
    print(f"✅ Ouverture du paquet pour Gaël Djebar")
else:
    print(f"❌ Erreur lors de l'ouverture du paquet pour Gaël Djebar : {response.status_code} - {response.text}")

response = requests.get("http://localhost:8080/dresseurs", headers=HEADERS)
if response.status_code == 200:
    pokemon1 = response.json()[0]["sideDeck"][0]["uuid"]
    pokemon2 = response.json()[1]["sideDeck"][0]["uuid"]
    pokemon3 = response.json()[2]["sideDeck"][0]["uuid"]
    print(f"✅ Clément possède un {response.json()[0]['sideDeck'][0]['nom']}, Mathieu possède un {response.json()[1]['sideDeck'][0]['nom']} et Gaël possède un {response.json()[2]['sideDeck'][0]['nom']}")
else:
    print(f"❌ Erreur lors de l'obtention des dresseurs : {response.status_code} - {response.text}")

response = requests.post("http://localhost:8080/dresseurs/echanger", json={"dresseur1Uuid": dresseur1, 
                                                                           "dresseur2Uuid": dresseur2,
                                                                           "pokemon1Uuid": pokemon1,
                                                                           "pokemon2Uuid": pokemon2
                                                                           }, headers=HEADERS)
if response.status_code == 200:
    print(f"✅ Echange effectué")
else:
    print(f"❌ Erreur lors de l'échange : {response.status_code} - {response.text}")

response = requests.get("http://localhost:8080/dresseurs", headers=HEADERS)
if response.status_code == 200:
    pokemon1 = response.json()[0]["sideDeck"][0]["uuid"]
    pokemon2 = response.json()[1]["sideDeck"][0]["uuid"]
    pokemon3 = response.json()[2]["sideDeck"][0]["uuid"]
    print(f"✅ Clément possède un {response.json()[0]['sideDeck'][0]['nom']}, Mathieu possède un {response.json()[1]['sideDeck'][0]['nom']} et Gaël possède un {response.json()[2]['sideDeck'][0]['nom']}")
else:
    print(f"❌ Erreur lors de l'obtention des dresseurs : {response.status_code} - {response.text}")

response = requests.post("http://localhost:8080/dresseurs/echanger", json={"dresseur1Uuid": dresseur1, 
                                                                           "dresseur2Uuid": dresseur3,
                                                                           "pokemon1Uuid": pokemon1,
                                                                           "pokemon2Uuid": pokemon3
                                                                           }, headers=HEADERS)
if response.status_code == 200:
    print(f"✅ Echange effectué")
else:
    print(f"❌ Erreur lors de l'échange : {response.status_code} - {response.text}")

response = requests.get("http://localhost:8080/dresseurs", headers=HEADERS)
if response.status_code == 200:
    pokemon1 = response.json()[0]["sideDeck"][0]["uuid"]
    pokemon2 = response.json()[1]["sideDeck"][0]["uuid"]
    pokemon3 = response.json()[2]["sideDeck"][0]["uuid"]
    print(f"✅ Clément possède un {response.json()[0]['sideDeck'][0]['nom']}, Mathieu possède un {response.json()[1]['sideDeck'][0]['nom']} et Gaël possède un {response.json()[2]['sideDeck'][0]['nom']}")
else:
    print(f"❌ Erreur lors de l'obtention des dresseurs : {response.status_code} - {response.text}")


response = requests.get("http://localhost:8080/dresseurs/historique", json={}, headers=HEADERS)
if response.status_code == 200:
    print(f"✅ Historique récupéré.")
else:
    print(f"❌ Historique non récupéré: {response.status_code} - {response.text}")

response = requests.post("http://localhost:8080/dresseurs/echanger", json={"dresseur1Uuid": dresseur2, 
                                                                           "dresseur2Uuid": dresseur3,
                                                                           "pokemon1Uuid": pokemon2,
                                                                           "pokemon2Uuid": pokemon3
                                                                           }, headers=HEADERS)
if response.status_code == 200:
    print(f"✅ Echange effectué")
else:
    print(f"❌ Erreur lors de l'échange : {response.status_code} - {response.text}")

response = requests.get("http://localhost:8080/dresseurs/historique", json={}, headers=HEADERS)
if response.status_code == 200:
    print(f"✅ Historique récupéré.")
else:
    print(f"❌ Historique non récupéré: {response.status_code} - {response.text}")

response = requests.post(f"http://localhost:8080/dresseurs/{dresseur1}/echanger-decks/{pokemon1}", headers=HEADERS)

if response.status_code == 200:
    print(f"✅ Side deck changé pour Clément Besnard")
else:
    print(f"❌ : {response.status_code} - {response.text}")


