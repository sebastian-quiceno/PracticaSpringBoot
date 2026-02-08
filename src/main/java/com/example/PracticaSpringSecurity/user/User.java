package com.example.PracticaSpringSecurity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;


//Cuando se usa Spring Security, cada que se haga un request, va a tener que pasar por un filtro,
//y la Etidad que tenga la informacion de los usuarios, debe de implementar la interfaz UserDetails
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//si no se le da el nombre, va a tomar el nombre de la entidad como el nombre default
@Table(name = "_user")
//Ayuda a contruir el objeto de una manera mas simple usando el patron de diseño builder
public class User implements UserDetails {

    @Id
    @GeneratedValue
    @SequenceGenerator

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Rol role;

    //Hay que tener en cuenta que estos metodos pueden ser hechos por lombok, por lo que se pueden "obviar"
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //ESte metodo debe retornar una lista de reglas,
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    //Se va a usar como Username el Correo
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
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
