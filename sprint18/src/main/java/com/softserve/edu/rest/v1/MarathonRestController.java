package com.softserve.edu.rest.v1;

import com.softserve.edu.dto.MarathonDTO;
import com.softserve.edu.model.Marathon;
import com.softserve.edu.service.MarathonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/marathons")
public class MarathonRestController {
    private final MarathonService marathonService;

    @Autowired
    public MarathonRestController(MarathonService marathonService) {
        this.marathonService = marathonService;
    }

    @GetMapping
    public ResponseEntity<List<MarathonDTO>> getAllMarathons() {
        List<MarathonDTO> marathons = marathonService.getAll().stream()
                .map(marathon -> MarathonDTO.of(marathon))
                .collect(Collectors.toList());
        return new ResponseEntity<>(marathons, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<MarathonDTO> getMarathonById(@PathVariable Long id) {
        MarathonDTO marathon = MarathonDTO.of(marathonService.getMarathonById(id));
        return new ResponseEntity<>(marathon, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_MENTOR')")
    @PostMapping
    public ResponseEntity<MarathonDTO> createNewMarathon(@Validated @RequestBody Marathon marathon) {
        MarathonDTO marathonDB = MarathonDTO.of(marathonService.createOrUpdate(marathon));
        return new ResponseEntity<>(marathonDB, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<MarathonDTO> updateMarathon(@PathVariable long id, @RequestBody Marathon marathon) {
        Marathon marathonDB = marathonService.getMarathonById(id);
        marathonDB.setTitle(marathon.getTitle());
        MarathonDTO marathonDTO = MarathonDTO.of(marathonService.createOrUpdate(marathonDB));
        return new ResponseEntity<>(marathonDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMarathon(@PathVariable Long id) {
        marathonService.deleteMarathonById(id);
    }
}
