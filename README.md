
# Pokémon TCG API

**Projet réalisé par Hamid DIAW & Clément BESNARD**

*Un fichier python de test est disponible pour tester l'api si besoin.*

## Routes  

### Dresseurs

#### GET /dresseurs

* **Description** : Récupère la liste de tous les dresseurs.
* **Réponse** :
  * [`200 OK`](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) : Liste des dresseurs.

#### POST /dresseurs

* **Description** : Crée un nouveau dresseur.
* **Paramètres** :
  * [**nom**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (String) : Nom du dresseur (obligatoire).
  * [**prenom**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (String) : Prénom du dresseur (obligatoire).
* **Réponse** :
  * `201 Created` : Dresseur créé avec succès.
  * [`400 Bad Request`](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) : Tous les champs sont obligatoires.

#### DELETE /dresseurs/

* **Description** : Supprime un dresseur.
* **Paramètres** :
  * [**uuid**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (String) : UUID du dresseur à supprimer.
* **Réponse** :
  * `204 No Content` : Dresseur supprimé avec succès.

#### POST /dresseurs/(uuid)/ouvrir-paquet

* **Description** : Ouvre un paquet de cartes pour un dresseur.
* **Paramètres** :
  * [**uuid**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (String) : UUID du dresseur.
* **Réponse** :
  * `201 Created` : Paquet ouvert avec succès.
  * [`400 Bad Request`](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) : Vous avez déjà ouvert un paquet aujourd'hui.

#### POST /dresseurs/echanger

* **Description** : Échange des Pokémon entre deux dresseurs.
* **Paramètres** :
  * [**dresseur1Uuid**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (String) : UUID du premier dresseur.
  * [**dresseur2Uuid**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (String) : UUID du deuxième dresseur.
  * [**pokemon1Uuid**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (String) : UUID du Pokémon du premier dresseur.
  * [**pokemon2Uuid**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (String) : UUID du Pokémon du deuxième dresseur.
* **Réponse** :
  * [`200 OK`](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) : Échange effectué avec succès.
  * [`400 Bad Request`](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) : Échange impossible.

#### POST /dresseurs/([**dresseurUuid**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html))/echanger-decks/([**pokemonUuid**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html))

* **Description** : Échange un Pokémon entre le main deck et le side deck d'un dresseur.
* **Paramètres** :
  * [**dresseurUuid**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (String) : UUID du dresseur.
  * [**pokemonUuid**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (String) : UUID du Pokémon à échanger.
* **Réponse** :
  * [`200 OK`](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) : Échange effectué avec succès.
  * [`400 Bad Request`](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) : Échange impossible.

#### GET /dresseurs/historique

* **Description** : Récupère l'historique des échanges.
* **Paramètres** (optionnels) :
  * [**year**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (Integer) : Année de l'historique.
  * [**month**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (Integer) : Mois de l'historique.
  * [**day**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (Integer) : Jour de l'historique.
* **Réponse** :
  * [`200 OK`](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) : Historique des échanges.

#### GET /dresseurs/([**uuid**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html))/historique

* **Description** : Récupère l'historique des échanges d'un dresseur.
* **Paramètres** :
  * [**uuid**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (String) : UUID du dresseur.
* **Réponse** :
  * [`200 OK`](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) : Historique des échanges du dresseur.

#### POST /dresseurs/([**dresseur1Uuid**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html))/defi/([**dresseur2Uuid**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html))

* **Description** : Lance un défi entre deux dresseurs.
* **Paramètres** :
  * [**dresseur1Uuid**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (String) : UUID du premier dresseur.
  * [**dresseur2Uuid**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (String) : UUID du deuxième dresseur.
* **Réponse** :
  * [`200 OK`](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) : Défi effectué avec succès.
  * [`400 Bad Request`](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) : Défi impossible.

### Pokémons

#### GET /pokemons

* **Description** : Récupère la liste de tous les Pokémon.
* **Paramètres** (optionnels) :
  * [**type**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (TypePokemon) : Type de Pokémon à filtrer.
* **Réponse** :
  * [`200 OK`](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) : Liste des Pokémon.

#### GET /pokemons/{uuid}

* **Description** : Récupère un Pokémon par son UUID.
* **Paramètres** :
  * [**uuid**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (String) : UUID du Pokémon.
* **Réponse** :
  * [`200 OK`](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) : Pokémon trouvé.
  * `404 Not Found` : Pokémon non trouvé.

#### POST /pokemons

* **Description** : Crée un nouveau Pokémon.
* **Paramètres** :
  * [**nom**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (String) : Nom du Pokémon (obligatoire).
  * [**type**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (TypePokemon) : Type du Pokémon (obligatoire).
  * [**rarity**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (Integer) : Rareté du Pokémon (obligatoire, entre 1 et 5).
  * [**attack1**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (String) : Première attaque du Pokémon (obligatoire).
  * [**attack2**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (String) : Deuxième attaque du Pokémon (obligatoire).
* **Réponse** :
  * `201 Created` : Pokémon créé avec succès.
  * [`400 Bad Request`](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) : Tous les champs sont obligatoires ou la rareté doit être comprise entre 1 et 5.

#### PUT /pokemons/(uuid)

* **Description** : Met à jour un Pokémon.
* **Paramètres** :
  * [**uuid**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (String) : UUID du Pokémon à mettre à jour.
  * [**nom**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (String) : Nom du Pokémon (obligatoire).
  * [**type**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (TypePokemon) : Type du Pokémon (obligatoire).
  * [**rarity**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (Integer) : Rareté du Pokémon (obligatoire, entre 1 et 5).
  * [**attack1**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (String) : Première attaque du Pokémon (obligatoire).
  * [**attack2**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (String) : Deuxième attaque du Pokémon (obligatoire).
* **Réponse** :
  * `204 No Content` : Pokémon mis à jour avec succès.
  * [`400 Bad Request`](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) : Tous les champs sont obligatoires ou la rareté doit être comprise entre 1 et 5.
  * `404 Not Found` : Pokémon non trouvé.

#### DELETE /pokemons/(uuid)

* **Description** : Supprime un Pokémon.
* **Paramètres** :
  * [**uuid**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) (String) : UUID du Pokémon à supprimer.
* **Réponse** :
  * `204 No Content` : Pokémon supprimé avec succès.
  * `404 Not Found` : Pokémon non trouvé.

## Authentification

Toutes les routes nécessitent une authentification via un header [**X-API-KEY**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html) avec la valeur [**ProjectToken**](vscode-file://vscode-app/c:/Users/Clems/AppData/Local/Programs/Microsoft%20VS%20Code/resources/app/out/vs/code/electron-sandbox/workbench/workbench.html).

## Exemples de requêtes


* Echanger le pokémon de deck

  ```shell

  curl -X POST http://localhost:8080/dresseurs/{uuiddresseur}/echanger-decks/{uuidpkm} -H "X-API-KEY: ProjectToken"

  ```
* Créer un dresseur

  ```shell

  curl -X POST http://localhost:8080/dresseurs -H "Content-Type: application/json" -H "X-API-KEY: ProjectToken" -d '{"nom":"Dupont", "prenom":"Jean"}'

  ```
* Ouvrir un paquet
  {

  ```shell

  curl -X POST http://localhost:8080/dresseurs/{uuid}/ouvrir-paquet -H "X-API-KEY: ProjectToken"

  ```
* Échanger des Pokémon entre dresseurs

  ```shell

  curl -X POST http://localhost:8080/dresseurs/echanger -H "Content-Type: application/json" -H "X-API-KEY: ProjectToken" -d '{"dresseur1Uuid":"{uuid1}", "dresseur2Uuid":"{uuid2}", "pokemon1Uuid":"{pokemonUuid1}", "pokemon2Uuid":"{pokemonUuid2}"}'

  ```
* Lancer un défi

  ```shell

  curl -X POST http://localhost:8080/dresseurs/{dresseur1Uuid}/defi/{dresseur2Uuid} -H "X-API-KEY: ProjectToken"

  ```
* Créer un Pokémon

  ```shell

  curl -X POST http://localhost:8080/pokemons -H "Content-Type: application/json" -H "X-API-KEY: ProjectToken" -d '{"nom":"Pikachu", "type":"ELECTRIQUE", "rarity":2, "attack1":"Éclair", "attack2":"Tonnerre"}'

  ```
* Mettre à jour un Pokémon

  ```shell

  curl -X PUT http://localhost:8080/pokemons/{uuid} -H "Content-Type: application/json" -H "X-API-KEY: ProjectToken" -d '{"nom":"Pikachu", "type":"ELECTRIQUE", "rarity":3, "attack1":"Éclair", "attack2":"Tonnerre"}'

  ```
* Supprimer un Pokémon

  ```shell

  curl -X DELETE http://localhost:8080/pokemons/{uuid} -H "X-API-KEY: ProjectToken"

  ```
* Récupérer l'historique des échanges

  ```shell

  curl -X GET http://localhost:8080/dresseurs/historique -H "X-API-KEY: ProjectToken"

  ```
* Récupérer l'historique des échanges d'un dresseur

  ```shell

  curl -X GET http://localhost:8080/dresseurs/{uuid}/historique -H "X-API-KEY: ProjectToken"

  ```
