package ma.beans.classes;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "hommes")
@PrimaryKeyJoinColumn(name = "personne_id")
public class Homme extends Personne {

    public Homme() {}

    public Homme(String nom, String prenom, java.time.LocalDate dateNaissance, String lieuNaissance) {
        super(nom, prenom, dateNaissance, lieuNaissance);
    }
}
