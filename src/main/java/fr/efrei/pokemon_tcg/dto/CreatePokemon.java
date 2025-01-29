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

    @Length(min = 3, max = 50)
    private String attack1;

    @Length(min = 3, max = 50)
    private String attack2;

    public String getNom() {
        return nom;
    }

    public Integer getRarity() {
        return rarity;
    }

    public TypePokemon getType() {
        return type;
    }

    public String getAttack1() {
        return attack1;
    }

    public String getAttack2() {
        return attack2;
    }
}
