package com.openclassrooms.mddapi.modles;






import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;
import lombok.*;




@Data   
@Builder
@NoArgsConstructor 
@AllArgsConstructor
@Entity(name = "article") 
@Table(name = "article")
public class Article{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private  Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;


    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "article_theme",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "theme_id") })
    @ToString.Exclude
    private List<Theme> articleThemes = new ArrayList<Theme>();


    public void addTheme(Theme theme) {
        this.articleThemes.add(theme);
        theme.getArticles().add(this);
    }

    public void removeTheme(Integer themeId) {
        Theme theme = this.articleThemes.stream().filter(t -> t.getId() == themeId).findFirst().orElse(null);
        if (theme != null) {
            this.articleThemes.remove(theme);
            theme.getUsers().remove(this);
        }
    }

    @Column(name = "author")
    private String author;      //* userId

    @Column(name = "comment")
    private String comment;      //* [comment1, comment2 ...]

   

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date created_at;

    @CreationTimestamp
    @Column(name = "updated_at", updatable = true)
    private Date updated_at;


    
}
