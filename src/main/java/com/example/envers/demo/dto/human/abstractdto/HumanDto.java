package com.example.envers.demo.dto.human.abstractdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class HumanDto {

  private Long id;

  private String name;

  private Integer age;

  private String status;

  private Integer orderId;
}
