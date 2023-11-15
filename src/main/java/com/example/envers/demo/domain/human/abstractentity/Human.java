package com.example.envers.demo.domain.human.abstractentity;

import static jakarta.persistence.GenerationType.AUTO;
import static jakarta.persistence.InheritanceType.TABLE_PER_CLASS;

import com.example.envers.demo.domain.university.University;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@Audited
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = TABLE_PER_CLASS)
public abstract class Human {

  @Id
  @GeneratedValue(strategy = AUTO)
  private Long id;

  private String name;

  private Integer age;

  @ManyToOne
  @JoinColumn(name = "university_id")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private University university;

  @Column(name = "order_id")
  private Integer orderId;
}

