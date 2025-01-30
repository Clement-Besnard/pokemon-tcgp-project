package fr.efrei.pokemon_tcg.services;

import fr.efrei.pokemon_tcg.dto.DresseurDTO;
import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.models.Echange;

import java.time.LocalDateTime;
import java.util.List;

public interface IDresseurService {

    List<Dresseur> findAll();
    Dresseur findById(String uuid);
    void create(DresseurDTO dresseurDTO);

    boolean update(String uuid, DresseurDTO dresseurDTO);
    boolean delete(String uuid);

    boolean echangerPokemons(String dresseur1Uuid, String dresseur2Uuid, String pokemon1Uuid, String pokemon2Uuid);
    boolean echangerDecks(String dresseurUuid, String pokemonUuid);

    List<Echange> getHistoriqueEchanges(LocalDateTime start, LocalDateTime end);
    List<Echange> getHistoriqueEchangesDresseur(String dresseurUuid);
}
