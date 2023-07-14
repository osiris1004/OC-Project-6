package com.openclassrooms.mddapi.modles;






import java.sql.Date;
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

    @Column(name = "theme")
    private String theme;  //* [theme1, theme2, theme3 ...]

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
