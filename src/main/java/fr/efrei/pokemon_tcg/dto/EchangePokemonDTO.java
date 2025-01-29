package fr.efrei.pokemon_tcg.dto;

public class EchangePokemonDTO {
    private String dresseur1Uuid;
    private String dresseur2Uuid;
    private String pokemon1Uuid;
    private String pokemon2Uuid;

    public String getDresseur1Uuid() {
        return dresseur1Uuid;
    }

    public void setDresseur1Uuid(String dresseur1Uuid) {
        this.dresseur1Uuid = dresseur1Uuid;
    }

    public String getDresseur2Uuid() {
        return dresseur2Uuid;
    }

    public void setDresseur2Uuid(String dresseur2Uuid) {
        this.dresseur2Uuid = dresseur2Uuid;
    }

    public String getPokemon1Uuid() {
        return pokemon1Uuid;
    }

    public void setPokemon1Uuid(String pokemon1Uuid) {
        this.pokemon1Uuid = pokemon1Uuid;
    }

    public String getPokemon2Uuid() {
        return pokemon2Uuid;
    }

    public void setPokemon2Uuid(String pokemon2Uuid) {
        this.pokemon2Uuid = pokemon2Uuid;
    }
}
