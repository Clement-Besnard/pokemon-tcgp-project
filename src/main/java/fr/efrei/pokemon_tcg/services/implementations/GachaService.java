package fr.efrei.pokemon_tcg.services.implementations;

import fr.efrei.pokemon_tcg.constants.PokemonTemplates;
import fr.efrei.pokemon_tcg.constants.TypePokemon;
import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.models.Pokemon;
import fr.efrei.pokemon_tcg.repositories.DresseurRepository;
import fr.efrei.pokemon_tcg.repositories.PokemonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public static class PokemonTemplate {
        String nom;
        int rarity;
        TypePokemon type;

        public PokemonTemplate(String nom, int rarity, TypePokemon type) {
            this.nom = nom;
            this.rarity = rarity;
            this.type = type;
        }
    }

    public void ouvrirPaquet(String dresseurUuid) {
        Dresseur dresseur = dresseurRepository.findById(dresseurUuid).orElseThrow(() -> new IllegalArgumentException("Dresseur non trouvé"));

        LocalDateTime now = LocalDateTime.now();
        if (dresseur.getLastPackOpenedAt() != null && dresseur.getLastPackOpenedAt().toLocalDate().isEqual(now.toLocalDate())) {
            throw new IllegalArgumentException("Vous avez déjà ouvert un paquet aujourd'hui.");
        }

        for (int i = 0; i < 5; i++) {
            PokemonTemplate template = genererPokemonTemplate();
            Pokemon pokemon = new Pokemon();
            pokemon.setNom(template.nom);
            pokemon.setRarity(template.rarity);
            pokemon.setType(template.type);

            pokemonRepository.save(pokemon);
            dresseur.getSideDeck().add(pokemon);
        }

        dresseur.setLastPackOpenedAt(now);
        dresseurRepository.save(dresseur);
    }

    private PokemonTemplate genererPokemonTemplate() {
        Random random = new Random();
        int rarity = genererRarity();
        List<PokemonTemplate> filteredTemplates = new ArrayList<>();
        for (PokemonTemplate template : PokemonTemplates.POKEMON_TEMPLATES) {
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