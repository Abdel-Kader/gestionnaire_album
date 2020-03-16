package entities;

import javax.persistence.*;

@Entity
@Table(name = "type_utilisateur")
public class TypeUtilisateur
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type_utilisateur;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getType_utilisateur()
    {
        return type_utilisateur;
    }

    public void setType_utilisateur(String type_utilisateur)
    {
        this.type_utilisateur = type_utilisateur;
    }


}
