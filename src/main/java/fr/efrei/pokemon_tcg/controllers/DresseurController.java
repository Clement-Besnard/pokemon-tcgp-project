package fr.efrei.pokemon_tcg.controllers;

import fr.efrei.pokemon_tcg.dto.DresseurDTO;
import fr.efrei.pokemon_tcg.dto.EchangePokemonDTO;
import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.models.Echange;
import fr.efrei.pokemon_tcg.services.IDresseurService;
import fr.efrei.pokemon_tcg.services.implementations.DresseurServiceImpl;
import fr.efrei.pokemon_tcg.services.implementations.GachaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/dresseurs")
public class DresseurController {

    private final IDresseurService dresseurService;
    private final GachaService gachaService;

    public DresseurController(DresseurServiceImpl dresseurService, GachaService gachaService) {
        this.dresseurService = dresseurService;
        this.gachaService = gachaService;
    }

    @GetMapping
    public ResponseEntity<List<Dresseur>> findAll() {
        return new ResponseEntity<>(dresseurService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DresseurDTO dresseurDTO) {
        if (dresseurDTO.getPrenom() == null || dresseurDTO.getNom() == null) {
            return new ResponseEntity<>("Tous les champs sont obligatoires.", HttpStatus.BAD_REQUEST);
        }
        dresseurService.create(dresseurDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable String uuid) {
        dresseurService.delete(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{uuid}/ouvrir-paquet")
    public ResponseEntity<?> ouvrirPaquet(@PathVariable String uuid) {
        gachaService.ouvrirPaquet(uuid);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/echanger")
    public ResponseEntity<?> echangerPokemons(@RequestBody EchangePokemonDTO echangePokemonDTO) {
        boolean isEchange = dresseurService.echangerPokemons(
                echangePokemonDTO.getDresseur1Uuid(),
                echangePokemonDTO.getDresseur2Uuid(),
                echangePokemonDTO.getPokemon1Uuid(),
                echangePokemonDTO.getPokemon2Uuid()
        );
        if (!isEchange) {
            return new ResponseEntity<>("Échange impossible.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{dresseurUuid}/echanger-decks/{pokemonUuid}")
    public ResponseEntity<?> echangerDecks(@PathVariable String dresseurUuid, @PathVariable String pokemonUuid) {
        boolean isEchange = dresseurService.echangerDecks(dresseurUuid, pokemonUuid);
        if (!isEchange) {
            return new ResponseEntity<>("Échange impossible.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/historique")
    public ResponseEntity<List<Echange>> getHistoriqueEchanges(@RequestParam(required = false) Integer year, @RequestParam(required = false) Integer month, @RequestParam(required = false) Integer day) {
        LocalDateTime start;
        LocalDateTime end;

        if (year != null && month != null && day != null) {
            start = LocalDateTime.of(LocalDate.of(year, month, day), LocalTime.MIN);
            end = LocalDateTime.of(LocalDate.of(year, month, day), LocalTime.MAX);
        } else if (year != null && month != null) {
            start = LocalDateTime.of(LocalDate.of(year, month, 1), LocalTime.MIN);
            end = LocalDateTime.of(LocalDate.of(year, month, LocalDate.of(year, month, 1).lengthOfMonth()), LocalTime.MAX);
        } else if (year != null) {
            start = LocalDateTime.of(LocalDate.of(year, 1, 1), LocalTime.MIN);
            end = LocalDateTime.of(LocalDate.of(year, 12, 31), LocalTime.MAX);
        } else {
            start = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
            end = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        }

        List<Echange> echanges = dresseurService.getHistoriqueEchanges(start, end);
        return new ResponseEntity<>(echanges, HttpStatus.OK);
    }

    @GetMapping("/{uuid}/historique")
    public ResponseEntity<List<Echange>> getHistoriqueEchangesDresseur(@PathVariable String uuid) {
        List<Echange> echanges = dresseurService.getHistoriqueEchangesDresseur(uuid);
        return new ResponseEntity<>(echanges, HttpStatus.OK);
    }

    @PostMapping("/{dresseur1Uuid}/defi/{dresseur2Uuid}")
    public ResponseEntity<?> defi(@PathVariable String dresseur1Uuid, @PathVariable String dresseur2Uuid) {
        boolean isDefi = dresseurService.defi(dresseur1Uuid, dresseur2Uuid);
        if (!isDefi) {
            return new ResponseEntity<>("Défi impossible.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
