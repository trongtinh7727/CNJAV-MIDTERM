package com.iiex.javamidterm.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.util.Collection;

@Entity
@Data
@Table(name = "`color`")
@NoArgsConstructor
@AllArgsConstructor
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String code;

    @ManyToMany(mappedBy = "colors")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Collection<Product> products;
}
