package com.iiex.javamidterm.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Product> products;

}

