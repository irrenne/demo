package com.example.envers.demo.service;

import com.example.envers.demo.domain.human.Student;
import com.example.envers.demo.domain.human.Teacher;
import com.example.envers.demo.domain.human.abstractentity.Human;
import com.example.envers.demo.domain.university.University;
import com.example.envers.demo.dto.university.UniversityDto;
import com.example.envers.demo.mapper.UniversityMapper;
import com.example.envers.demo.repository.UniversityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UniversityService {

  private final UniversityRepository universityRepository;
  private final UniversityMapper universityMapper;

  @PersistenceContext
  private EntityManager entityManager;

  public UniversityDto preCreateUniversity() {
    University university = createUniversity();
    University savedUniversity = universityRepository.save(university);
    return universityMapper.toDto(savedUniversity);
  }

  public List<UniversityDto> getUniversityAndHumanHistory(Long universityId) {
    AuditReader auditReader = AuditReaderFactory.get(entityManager);
    List<Number> revisionNumbers = auditReader.getRevisions(University.class, universityId);
    log.info("Revision: %s".formatted(revisionNumbers));
    List<University> universities = revisionNumbers.stream()
        .map(revisionNumber -> auditReader.find(University.class, universityId, revisionNumber))
        .toList();
    return universities.stream().map(universityMapper::toDto).toList();
  }

  private University createUniversity() {
    University university = University.builder().name("CoolUni").build();
    Student student = createStudent();
    Teacher teacher = createTeacher();

    List<Human> humanList = new ArrayList<>();
    humanList.add(student);
    humanList.add(teacher);

    university.setHumanList(humanList);
    humanList.forEach(human -> human.setUniversity(university));

    return university;
  }

  private Student createStudent() {
    return Student.builder()
        .name("John Doe")
        .groupName("PMI-41")
        .age(20)
        .orderId(1)
        .build();
  }

  private Teacher createTeacher() {
    return Teacher.builder()
        .name("Sophie Maroon")
        .rank("Professor")
        .age(54)
        .orderId(2)
        .build();
  }
}
