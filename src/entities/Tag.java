package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tag
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tag;

    public Long getId()
    {
        return id;
    }

    @ManyToMany
    @JoinTable(
            name = "photo_tags",
            joinColumns = {@JoinColumn(name = "tag_id")},
            inverseJoinColumns = {@JoinColumn(name = "photo_id")}
    )
    private Set<Photo> photos = new HashSet<>();

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getTag()
    {
        return tag;
    }

    public void setTag(String tag)
    {
        this.tag = tag;
    }

    public Set<Photo> getPhotos()
    {
        return photos;
    }

    public void setPhotos(Set<Photo> photos)
    {
        this.photos = photos;
    }
}
