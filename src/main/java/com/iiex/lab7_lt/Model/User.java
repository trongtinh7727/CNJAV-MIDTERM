package com.iiex.lab7_lt.Model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;
  private String email;
  private String address;

  private String password;

  @Column(name = "created")
  @Temporal(TemporalType.TIMESTAMP)
  private java.util.Date created;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Collection<Transaction> transactions;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
    name = "users_roles",
    joinColumns = {
      @JoinColumn(name = "user_id", referencedColumnName = "id"),
    },
    inverseJoinColumns = {
      @JoinColumn(name = "role_id", referencedColumnName = "id"),
    }
  )
  private Collection<Role> roles;
}
