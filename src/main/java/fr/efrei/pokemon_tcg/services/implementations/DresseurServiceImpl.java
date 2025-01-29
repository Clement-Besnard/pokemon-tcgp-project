package fr.efrei.pokemon_tcg.services.implementations;

import fr.efrei.pokemon_tcg.dto.DresseurDTO;
import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.repositories.DresseurRepository;
import fr.efrei.pokemon_tcg.services.IDresseurService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DresseurServiceImpl implements IDresseurService {

    private final DresseurRepository repository;

    public DresseurServiceImpl(DresseurRepository repository) {
        this.repository = repository;
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
}
