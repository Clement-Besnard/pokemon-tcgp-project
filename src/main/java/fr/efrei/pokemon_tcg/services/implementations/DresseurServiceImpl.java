package fr.efrei.pokemon_tcg.services.implementations;

import fr.efrei.pokemon_tcg.dto.DresseurDTO;
import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.models.Echange;
import fr.efrei.pokemon_tcg.models.Pokemon;
import fr.efrei.pokemon_tcg.repositories.DresseurRepository;
import fr.efrei.pokemon_tcg.repositories.EchangeRepository;
import fr.efrei.pokemon_tcg.services.IDresseurService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Comparator;

@Service
public class DresseurServiceImpl implements IDresseurService {

    private final DresseurRepository repository;
    private final EchangeRepository echangeRepository;

    public DresseurServiceImpl(DresseurRepository repository, EchangeRepository echangeRepository) {
        this.repository = repository;
        this.echangeRepository = echangeRepository;
    }

    @Override
    public List<Dresseur> findAll() {
        return repository.findAllByDeletedAtNull();
    }

    @Override
    public Dresseur findById(String uuid) {
        return repository.findById(uuid).orElse(null);
    }

    @Override
    public void create(DresseurDTO dresseurDTO) {
        Dresseur dresseur = new Dresseur();
        dresseur.setNom(dresseurDTO.getNom());
        dresseur.setPrenom(dresseurDTO.getPrenom());
        dresseur.setDeletedAt(null);
        repository.save(dresseur);
    }

    @Override
    public boolean update(String uuid, DresseurDTO dresseurDTO) {
        Dresseur dresseur = findById(uuid);
        if (dresseur == null) {
            return false;
        }
        dresseur.setNom(dresseurDTO.getNom());
        dresseur.setPrenom(dresseurDTO.getPrenom());
        repository.save(dresseur);
        return true;
    }

    @Override
    public boolean delete(String uuid) {
        Dresseur dresseur = findById(uuid);
        if (dresseur == null) {
            return false;
        }
        dresseur.setDeletedAt(LocalDateTime.now());
        repository.save(dresseur);
        return true;
    }

    @Override
    public boolean echangerPokemons(String dresseur1Uuid, String dresseur2Uuid, String pokemon1Uuid, String pokemon2Uuid) {
        Dresseur dresseur1 = findById(dresseur1Uuid);
        Dresseur dresseur2 = findById(dresseur2Uuid);
        if (dresseur1 == null || dresseur2 == null) {
            return false;
        }

        Pokemon pokemon1 = dresseur1.getSideDeck().stream().filter(p -> p.getUuid().equals(pokemon1Uuid)).findFirst().orElse(null);
        Pokemon pokemon2 = dresseur2.getSideDeck().stream().filter(p -> p.getUuid().equals(pokemon2Uuid)).findFirst().orElse(null);
        if (pokemon1 == null || pokemon2 == null) {
            return false;
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfDay = now.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = now.toLocalDate().atTime(23, 59, 59);

        List<Echange> echanges = echangeRepository.findAllByDresseur1UuidAndDresseur2UuidAndDateEchangeBetween(dresseur1Uuid, dresseur2Uuid, startOfDay, endOfDay);
        if (!echanges.isEmpty()) {
            return false;
        }

        // Check if either dresseur already has the Pokémon they are supposed to receive
        if (dresseur1.getSideDeck().contains(pokemon2) || dresseur2.getSideDeck().contains(pokemon1)) {
            return false;
        }

        // Remove Pokémon from current side decks
		if (!dresseur1.getSideDeck().contains(pokemon2)) {
            dresseur1.getSideDeck().remove(pokemon1);
        }
        if (!dresseur2.getSideDeck().contains(pokemon1)) {
            dresseur2.getSideDeck().remove(pokemon2);
        }

		repository.save(dresseur1);
        repository.save(dresseur2);

        // Add Pokémon to new side decks if not already present
        if (!dresseur1.getSideDeck().contains(pokemon2)) {
            dresseur1.getSideDeck().add(pokemon2);
        }
        if (!dresseur2.getSideDeck().contains(pokemon1)) {
            dresseur2.getSideDeck().add(pokemon1);
        }

        Echange echange = new Echange();
        echange.setDresseur1(dresseur1);
        echange.setDresseur2(dresseur2);
        echange.setPokemon1(pokemon1);
        echange.setPokemon2(pokemon2);
        echange.setDateEchange(now);
        echangeRepository.save(echange);

        repository.save(dresseur1);
        repository.save(dresseur2);

        return true;
    }

    @Override
    public List<Echange> getHistoriqueEchanges(LocalDateTime start, LocalDateTime end) {
        return echangeRepository.findAllByDateEchangeBetween(start, end);
    }

    @Override
    public List<Echange> getHistoriqueEchangesDresseur(String dresseurUuid) {
        return echangeRepository.findAllByDresseur1UuidOrDresseur2Uuid(dresseurUuid, dresseurUuid);
    }

    @Override
    public boolean echangerDecks(String dresseurUuid, String pokemonUuid) {
        Dresseur dresseur = findById(dresseurUuid);
        if (dresseur == null) {
            return false;
        }

        Pokemon pokemon = dresseur.getMainDeck().stream()
                .filter(p -> p.getUuid().equals(pokemonUuid))
                .findFirst()
                .orElse(dresseur.getSideDeck().stream()
                        .filter(p -> p.getUuid().equals(pokemonUuid))
                        .findFirst()
                        .orElse(null));

        if (pokemon == null) {
            return false;
        }

        if (dresseur.getMainDeck().contains(pokemon)) {
            dresseur.getMainDeck().remove(pokemon);
            dresseur.getSideDeck().add(pokemon);
        } else {
            if (dresseur.getMainDeck().size() >= 5) {
                throw new IllegalArgumentException("Le mainDeck ne peut pas contenir plus de 5 éléments.");
            }
            dresseur.getSideDeck().remove(pokemon);
            dresseur.getMainDeck().add(pokemon);
        }

        repository.save(dresseur);
        return true;
    }

    @Override
    public boolean defi(String dresseur1Uuid, String dresseur2Uuid) {
        Dresseur dresseur1 = findById(dresseur1Uuid);
        Dresseur dresseur2 = findById(dresseur2Uuid);
        if (dresseur1 == null || dresseur2 == null) {
            return false;
        }

        if (dresseur1.getMainDeck().isEmpty() || dresseur2.getMainDeck().isEmpty()) {
            return false;
        }

        // Déterminer le vainqueur aléatoirement
        Random random = new Random();
        Dresseur vainqueur = random.nextBoolean() ? dresseur1 : dresseur2;
        Dresseur perdant = vainqueur == dresseur1 ? dresseur2 : dresseur1;

        // Trouver la meilleure carte du perdant
        Pokemon meilleureCarte = perdant.getMainDeck().stream()
                .max(Comparator.comparingInt(Pokemon::getRarity))
                .orElse(null);

        if (meilleureCarte == null) {
            return false;
        }

        // Ajouter la meilleure carte du perdant au sideDeck du vainqueur s'il ne la possède pas déjà
        if (!vainqueur.getSideDeck().contains(meilleureCarte)) {
            vainqueur.getSideDeck().add(meilleureCarte);
        }

        // Retirer la meilleure carte du mainDeck du perdant
        perdant.getMainDeck().remove(meilleureCarte);

        repository.save(vainqueur);
        repository.save(perdant);

        return true;
    }
}