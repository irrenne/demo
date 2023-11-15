package com.example.envers.demo.mapper;

import com.example.envers.demo.domain.human.Teacher;
import com.example.envers.demo.dto.human.TeacherDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

  Teacher toEntity(TeacherDto teacherDto);

  @Mapping(target = "status", constant = "Teacher")
  TeacherDto toDto(Teacher teacher);
}
