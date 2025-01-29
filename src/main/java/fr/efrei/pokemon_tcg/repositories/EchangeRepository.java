package fr.efrei.pokemon_tcg.repositories;

import fr.efrei.pokemon_tcg.models.Echange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EchangeRepository extends JpaRepository<Echange, String> {
    List<Echange> findAllByDateEchangeBetween(LocalDateTime start, LocalDateTime end);
    List<Echange> findAllByDresseur1UuidOrDresseur2Uuid(String dresseur1Uuid, String dresseur2Uuid);
    List<Echange> findAllByDresseur1UuidAndDresseur2UuidAndDateEchangeBetween(String dresseur1Uuid, String dresseur2Uuid, LocalDateTime start, LocalDateTime end);
}
