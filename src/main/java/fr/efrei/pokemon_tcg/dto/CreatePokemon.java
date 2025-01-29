package fr.efrei.pokemon_tcg.dto;

import fr.efrei.pokemon_tcg.constants.TypePokemon;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

public class CreatePokemon {

    @Length(min = 3, max = 20)
    private String nom;

    @Min(1)
    @Max(5)
    private Integer rarity;

    private TypePokemon type;

    public String getNom() {
        return nom;
    }

    public Integer getRarity() {
        return rarity;
    }

    public TypePokemon getType() {
        return type;
    }
}
