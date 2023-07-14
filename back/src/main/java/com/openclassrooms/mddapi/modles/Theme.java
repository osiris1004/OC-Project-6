package com.openclassrooms.mddapi.modles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openclassrooms.mddapi.modles.User.User;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "theme") 
@Table(name = "theme")
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private  Integer id;
    
    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "subscribedThemes")
    @JsonIgnore
    @ToString.Exclude
    private List<User> users =  new ArrayList<User>();
}   
