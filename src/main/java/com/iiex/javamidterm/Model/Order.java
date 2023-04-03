package com.iiex.javamidterm.Model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "`order`")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;
    private double price;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude
    private Product product;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Transaction transaction;
}
