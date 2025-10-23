package ma.beans.classes;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personnes")
@Inheritance(strategy = InheritanceType.JOINED)
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false, length = 100)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 100)
    private String prenom;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    @Column(name = "lieu_naissance", length = 100)
    private String lieuNaissance;

    @OneToMany(mappedBy = "homme", cascade = CascadeType.ALL)
    private List<Mariage> mariagesHomme = new ArrayList<>();

    @OneToMany(mappedBy = "femme", cascade = CascadeType.ALL)
    private List<Mariage> mariagesFemme = new ArrayList<>();

    // Constructeurs
    public Personne() {}

    public Personne(String nom, String prenom, LocalDate dateNaissance, String lieuNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }

    public String getLieuNaissance() { return lieuNaissance; }
    public void setLieuNaissance(String lieuNaissance) { this.lieuNaissance = lieuNaissance; }

    public List<Mariage> getMariagesHomme() { return mariagesHomme; }
    public void setMariagesHomme(List<Mariage> mariagesHomme) { this.mariagesHomme = mariagesHomme; }

    public List<Mariage> getMariagesFemme() { return mariagesFemme; }
    public void setMariagesFemme(List<Mariage> mariagesFemme) { this.mariagesFemme = mariagesFemme; }
}
