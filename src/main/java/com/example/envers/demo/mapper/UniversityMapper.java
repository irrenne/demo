package com.example.envers.demo.mapper;

import com.example.envers.demo.domain.human.Student;
import com.example.envers.demo.domain.human.Teacher;
import com.example.envers.demo.domain.human.abstractentity.Human;
import com.example.envers.demo.domain.university.University;
import com.example.envers.demo.dto.human.StudentDto;
import com.example.envers.demo.dto.human.TeacherDto;
import com.example.envers.demo.dto.human.abstractdto.HumanDto;
import com.example.envers.demo.dto.university.UniversityDto;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UniversityMapper {

  private final StudentMapper studentMapper;

  private final TeacherMapper teacherMapper;

  public University toEntity(UniversityDto universityDto) {
    University university = University.builder()
        .id(universityDto.getId())
        .name(universityDto.getName())
        .humanList(mapHumanListToEntity(universityDto.getPeople()))
        .build();
    university.getHumanList().forEach(h -> h.setUniversity(university));
    return university;
  }

  public UniversityDto toDto(University university) {
    return UniversityDto.builder()
        .id(university.getId())
        .name(university.getName())
        .people(mapHumanListToDto(university.getHumanList()))
        .build();
  }

  private List<HumanDto> mapHumanListToDto(List<Human> humanList) {
    List<HumanDto> humanDtos = new ArrayList<>();

    for (Human human : humanList) {
      switch (human.getClass().getSimpleName()) {
        case "Student" -> {
          Student student = (Student) human;
          humanDtos.add(studentMapper.toDto(student));
        }
        case "Teacher" -> {
          Teacher teacher = (Teacher) human;
          humanDtos.add(teacherMapper.toDto(teacher));
        }
        default -> throw new IllegalArgumentException("Unknown human type");
      }
    }
    return humanDtos;
  }

  private List<Human> mapHumanListToEntity(List<HumanDto> humanDtos) {
    List<Human> humanList = new ArrayList<>();

    for (HumanDto humanDto : humanDtos) {
      switch (humanDto.getStatus()) {
        case "Student" -> {
          StudentDto studentDto = (StudentDto) humanDto;
          humanList.add(studentMapper.toEntity(studentDto));
        }
        case "Teacher" -> {
          TeacherDto teacherDto = (TeacherDto) humanDto;

          humanList.add(teacherMapper.toEntity(teacherDto));
        }
        default -> throw new IllegalArgumentException("Unknown status: " + humanDto.getStatus());
      }
    }
    return humanList;
  }
}
