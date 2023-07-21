package com.openclassrooms.mddapi.modles.User;


import java.sql.Date;
import java.util.*;

import com.openclassrooms.mddapi.controllers.resquests.UserRequest;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.openclassrooms.mddapi.modles.Theme;

import jakarta.persistence.*;
import lombok.*;




@Data   
@Builder
@NoArgsConstructor 
@AllArgsConstructor
@Entity(name = "user") 
@Table(name = "user")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private  Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date created_at;

    @CreationTimestamp
    @Column(name = "updated_at", updatable = true)
    private Date updated_at;

    @Enumerated(EnumType.STRING) 
    private Role role;


    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_theme",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "theme_id") })
    @ToString.Exclude
    private List<Theme> subscribedThemes = new ArrayList<Theme>();


    public void addTheme(Theme theme) {
        this.subscribedThemes.add(theme);
        theme.getUsers().add(this);
    }

   public void removeTheme(Integer themeId) {
       Theme theme = this.subscribedThemes.stream().filter(t -> t.getId() == themeId).findFirst().orElse(null);
       if (theme != null) {
           this.subscribedThemes.remove(theme);
           theme.getUsers().remove(this);
       }
   }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name())); 
    }                                        

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
