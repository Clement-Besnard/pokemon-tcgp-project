package fr.efrei.pokemon_tcg.services.implementations;

import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.models.Pokemon;
import fr.efrei.pokemon_tcg.repositories.DresseurRepository;
import fr.efrei.pokemon_tcg.services.IPokemonService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GachaService {

    private final IPokemonService pokemonService;
    private final DresseurRepository dresseurRepository;

    public GachaService(IPokemonService pokemonService, DresseurRepository dresseurRepository) {
        this.pokemonService = pokemonService;
        this.dresseurRepository = dresseurRepository;
    }

    public void ouvrirPaquet(String dresseurUuid) {
        Dresseur dresseur = dresseurRepository.findById(dresseurUuid).orElseThrow(() -> new IllegalArgumentException("Dresseur non trouvé"));

        LocalDateTime now = LocalDateTime.now();
        if (dresseur.getLastPackOpenedAt() != null && dresseur.getLastPackOpenedAt().toLocalDate().isEqual(now.toLocalDate())) {
            throw new IllegalArgumentException("Vous avez déjà ouvert un paquet aujourd'hui.");
        }

        List<Pokemon> tousLesPokemons = pokemonService.findAll(null);
        List<Pokemon> nouveauxPokemons = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            Pokemon pokemon = tousLesPokemons.get(random.nextInt(tousLesPokemons.size()));
            nouveauxPokemons.add(pokemon);
        }

        dresseur.getSideDeck().addAll(nouveauxPokemons);
        dresseur.setLastPackOpenedAt(now);
        dresseurRepository.save(dresseur);
    }
}