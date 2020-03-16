package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Utilisateur
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String password;
    private String contact;
    private String login;
    private Date created_at;
    private Boolean by_admin;

    @ManyToOne
    private TypeUtilisateur type_utilisateur;

    @ManyToMany
    @JoinTable(
            name = "album_autorisation",
            joinColumns = {@JoinColumn(name = "utilisateur_id")},
            inverseJoinColumns = {@JoinColumn(name = "album_id")}
    )
    private Set<Album> albums = new HashSet<>();

    @OneToMany(mappedBy = "proprietaire", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Album> albumUser = new HashSet<>();

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public String getContact()
    {
        return contact;
    }

    public void setContact(String contact)
    {
        this.contact = contact;
    }

    public TypeUtilisateur getType_utilisateur()
    {
        return type_utilisateur;
    }

    public void setType_utilisateur(TypeUtilisateur type_utilisateur)
    {
        this.type_utilisateur = type_utilisateur;
    }

    public Date getCreated_at()
    {
        return created_at;
    }

    public void setCreated_at(Date created_at)
    {
        this.created_at = created_at;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public Set<Album> getAlbums()
    {
        return albums;
    }

    public void setAlbums(Set<Album> albums)
    {
        this.albums = albums;
    }

    public Boolean getBy_admin()
    {
        return by_admin;
    }

    public void setBy_admin(Boolean by_admin)
    {
        this.by_admin = by_admin;
    }

    public Set<Album> getAlbumUser()
    {
        return albumUser;
    }

    public void setAlbumUser(Set<Album> albumUser)
    {
        this.albumUser = albumUser;
    }

    public Utilisateur()
    {
        setCreated_at(new Date(System.currentTimeMillis()));
    }
}
