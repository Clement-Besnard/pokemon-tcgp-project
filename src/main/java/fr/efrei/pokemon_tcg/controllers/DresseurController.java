package fr.efrei.pokemon_tcg.controllers;

import fr.efrei.pokemon_tcg.dto.DresseurDTO;
import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.services.IDresseurService;
import fr.efrei.pokemon_tcg.services.implementations.DresseurServiceImpl;
import fr.efrei.pokemon_tcg.services.implementations.GachaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
