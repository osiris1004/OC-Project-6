package com.openclassrooms.mddapi.modles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data   
@Builder
@NoArgsConstructor 
@AllArgsConstructor
@Entity(name = "comment") 
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private  Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "content", length = 3000)
    private String content;
}
