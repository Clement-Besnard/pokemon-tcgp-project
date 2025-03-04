package fr.efrei.pokemon_tcg.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Dresseur {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String uuid;

	private String nom;

	private String prenom;

	private LocalDateTime deletedAt;

	private LocalDateTime lastPackOpenedAt;

	@OneToMany
	private List<Pokemon> mainDeck;

	@OneToMany
	private List<Pokemon> sideDeck;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}

	public LocalDateTime getLastPackOpenedAt() {
		return lastPackOpenedAt;
	}

	public void setLastPackOpenedAt(LocalDateTime lastPackOpenedAt) {
		this.lastPackOpenedAt = lastPackOpenedAt;
	}

	public List<Pokemon> getMainDeck() {
		return mainDeck;
	}

	public void setMainDeck(List<Pokemon> mainDeck) {
		if (mainDeck.size() > 5) {
			throw new IllegalArgumentException("Le mainDeck ne peut pas contenir plus de 5 éléments.");
		}
		this.mainDeck = mainDeck;
	}

	public List<Pokemon> getSideDeck() {
		return sideDeck;
	}

	public void setSideDeck(List<Pokemon> sideDeck) {
		this.sideDeck = sideDeck;
	}
}
