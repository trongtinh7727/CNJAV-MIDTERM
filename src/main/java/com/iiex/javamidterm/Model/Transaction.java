package com.iiex.javamidterm.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Collection;

import lombok.*;


@Entity
@Data
@Table(name = "`transaction`")
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int status;
  private double amount;
  private String payment;
  private String message;

  @Column(name = "created")
  @Temporal(TemporalType.TIMESTAMP)
  private java.util.Date created;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
  @ToString.Exclude
  private User user;
  @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
  // MapopedBy trỏ tới tên biến Address ở trong Person.
  @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
  @ToString.Exclude
  @JsonIgnore

  private Collection<Order> orders;


}
