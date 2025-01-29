package fr.efrei.pokemon_tcg.services.implementations;

import fr.efrei.pokemon_tcg.constants.TypePokemon;
import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.models.Pokemon;
import fr.efrei.pokemon_tcg.repositories.DresseurRepository;
import fr.efrei.pokemon_tcg.repositories.PokemonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GachaService {

    private final PokemonRepository pokemonRepository;
    private final DresseurRepository dresseurRepository;

    public GachaService(PokemonRepository pokemonRepository, DresseurRepository dresseurRepository) {
        this.pokemonRepository = pokemonRepository;
        this.dresseurRepository = dresseurRepository;
    }

    private static class PokemonTemplate {
        String nom;
        int rarity;
        TypePokemon type;

        PokemonTemplate(String nom, int rarity, TypePokemon type) {
            this.nom = nom;
            this.rarity = rarity;
            this.type = type;
        }
    }

    private static final List<PokemonTemplate> POKEMON_TEMPLATES = new ArrayList<>();

    static {
        POKEMON_TEMPLATES.add(new PokemonTemplate("Pikachu", 1, TypePokemon.ELECTRIQUE));
        POKEMON_TEMPLATES.add(new PokemonTemplate("Bulbizarre", 1, TypePokemon.PLANTE));
        POKEMON_TEMPLATES.add(new PokemonTemplate("Salamèche", 2, TypePokemon.FEU));
        POKEMON_TEMPLATES.add(new PokemonTemplate("Carapuce", 2, TypePokemon.EAU));
        POKEMON_TEMPLATES.add(new PokemonTemplate("Evoli", 3, TypePokemon.NORMAL));
        POKEMON_TEMPLATES.add(new PokemonTemplate("Dracaufeu", 4, TypePokemon.FEU));
        POKEMON_TEMPLATES.add(new PokemonTemplate("Mewtwo", 5, TypePokemon.PSY));
    }

    public void ouvrirPaquet(String dresseurUuid) {
        Dresseur dresseur = dresseurRepository.findById(dresseurUuid).orElseThrow(() -> new IllegalArgumentException("Dresseur non trouvé"));

        for (int i = 0; i < 5; i++) {
            PokemonTemplate template = genererPokemonTemplate();
            Pokemon pokemon = new Pokemon();
            pokemon.setNom(template.nom);
            pokemon.setRarity(template.rarity);
            pokemon.setType(template.type);

            pokemonRepository.save(pokemon);
            dresseur.getSideDeck().add(pokemon);
        }

        dresseurRepository.save(dresseur);
    }

    private PokemonTemplate genererPokemonTemplate() {
        Random random = new Random();
        int rarity = genererRarity();
        List<PokemonTemplate> filteredTemplates = new ArrayList<>();
        for (PokemonTemplate template : POKEMON_TEMPLATES) {
            if (template.rarity == rarity) {
                filteredTemplates.add(template);
            }
        }
        return filteredTemplates.get(random.nextInt(filteredTemplates.size()));
    }

    private int genererRarity() {
        Random random = new Random();
        int rand = random.nextInt(100) + 1;
        if (rand <= 50) {
            return 1;
        } else if (rand <= 80) {
            return 2;
        } else if (rand <= 95) {
            return 3;
        } else if (rand <= 99) {
            return 4;
        } else {
            return 5;
        }
    }
}