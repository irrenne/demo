package com.example.envers.demo.domain.university;

import com.example.envers.demo.domain.human.abstractentity.Human;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@Audited
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class University {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("orderId")
  private List<Human> humanList = new ArrayList<>();
}
