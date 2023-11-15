package com.example.envers.demo.controller;

import com.example.envers.demo.dto.university.UniversityDto;
import com.example.envers.demo.service.UniversityService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/university")
public class UniversityController {

  private final UniversityService universityService;

  @PostMapping("/pre-create")
  public UniversityDto createInitialUniversity() {
    return universityService.preCreateUniversity();
  }

  @GetMapping("/history/{universityId}")
  public List<UniversityDto> getUniversityHistory(@PathVariable Long universityId) {
    return universityService.getUniversityAndHumanHistory(universityId);
  }
}
