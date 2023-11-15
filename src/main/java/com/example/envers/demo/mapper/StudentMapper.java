package com.example.envers.demo.mapper;

import com.example.envers.demo.domain.human.Student;
import com.example.envers.demo.dto.human.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

  Student toEntity(StudentDto studentDto);

  @Mapping(target = "status", constant = "Student")
  StudentDto toDto(Student student);
}
