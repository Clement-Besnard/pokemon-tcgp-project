package fr.efrei.pokemon_tcg.constants;

import fr.efrei.pokemon_tcg.services.implementations.GachaService;

import java.util.ArrayList;
import java.util.List;

public class PokemonTemplates {

    public static final List<GachaService.PokemonTemplate> POKEMON_TEMPLATES = new ArrayList<>();

    static {
        POKEMON_TEMPLATES.add(new GachaService.PokemonTemplate("Bulbizarre", 1, TypePokemon.PLANTE));
        POKEMON_TEMPLATES.add(new GachaService.PokemonTemplate("Herbizarre", 2, TypePokemon.PLANTE));
        POKEMON_TEMPLATES.add(new GachaService.PokemonTemplate("Florizarre", 4, TypePokemon.PLANTE));
        POKEMON_TEMPLATES.add(new GachaService.PokemonTemplate("Salamèche", 2, TypePokemon.FEU));
        POKEMON_TEMPLATES.add(new GachaService.PokemonTemplate("Reptincel", 3, TypePokemon.FEU));
        POKEMON_TEMPLATES.add(new GachaService.PokemonTemplate("Dracaufeu", 4, TypePokemon.FEU));
    }
}