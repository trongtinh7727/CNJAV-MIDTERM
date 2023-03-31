package com.iiex.lab7_lt.Model;

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

    @ManyToMany(mappedBy = "colors")
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude
    private Collection<Product> products;
}
