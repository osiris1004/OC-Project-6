package com.openclassrooms.mddapi.modles;

import com.openclassrooms.mddapi.modles.User.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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

    @ManyToOne(fetch =FetchType.LAZY)     //*- 
    @JoinColumn(name = "user_id")           //*- ---> identical to FK
    private User user;                  //*- 
}   
