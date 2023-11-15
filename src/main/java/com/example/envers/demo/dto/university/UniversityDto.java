package com.example.envers.demo.dto.university;

import com.example.envers.demo.dto.human.abstractdto.HumanDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UniversityDto {

  private Long id;

  private String name;

  List<HumanDto> people;
}
