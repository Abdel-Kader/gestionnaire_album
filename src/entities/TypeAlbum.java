package entities;

import javax.persistence.*;

@Entity
@Table(name = "type_album")
public class TypeAlbum
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type_album;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getType_album()
    {
        return type_album;
    }

    public void setType_album(String type_album)
    {
        this.type_album = type_album;
    }
}
