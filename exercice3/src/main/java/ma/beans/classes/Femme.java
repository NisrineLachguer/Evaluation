package ma.beans.classes;

import javax.persistence.*;

@Entity
@Table(name = "femmes")
@PrimaryKeyJoinColumn(name = "personne_id")
public class Femme extends Personne {

    public Femme() {}

    public Femme(String nom, String prenom, java.time.LocalDate dateNaissance, String lieuNaissance) {
        super(nom, prenom, dateNaissance, lieuNaissance);
    }
}
