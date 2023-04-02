package com.iiex.lab7_lt.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private float price;

    public void copy(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.content = product.getContent();
        this.img_links = product.getImg_links();
        this.brand = product.getBrand();
        this.category = product.getCategory();
    }

    private String content;

    private String img_links;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date created;


    @ManyToOne
    @JoinColumn(name = "brand_id")
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    // MapopedBy trỏ tới tên biến Address ở trong Person.
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Order> orders;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(
            name = "product_color",
            joinColumns = {
                    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "id"),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "COLOR_ID", referencedColumnName = "id"),
            }
    )
    private Collection<Color> colors;
}
