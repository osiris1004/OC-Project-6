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

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date created_at;

    @CreationTimestamp
    @Column(name = "updated_at", updatable = true)
    private Date updated_at;

    @Column(name = "author")
    private String author;      //* userId

    public  Article(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    ////**********
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "article_theme",
            joinColumns = { @JoinColumn(name = "article_id") },
            inverseJoinColumns = { @JoinColumn(name = "theme_id") })
    //@ToString.Exclude
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


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "comment_id")
    @Column(name = "comment")
    private List<Comment> comment = new ArrayList<Comment>();
       //* [comment1, comment2 ...]

   




    
}
