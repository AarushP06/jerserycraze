package com.champsoft.jerserycrazedatabase.presentation.controller;
import com.champsoft.jerserycrazedatabase.business.JerseyService;
import com.champsoft.jerserycrazedatabase.presentation.dto.Jersey.JerseyRequest;
import com.champsoft.jerserycrazedatabase.presentation.dto.Jersey.JerseyResponse;
import com.champsoft.jerserycrazedatabase.presentation.mapper.JerseyMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/jerseys")
public class JerseyController {

    private final JerseyService jerseyService;

    public JerseyController(JerseyService jerseyService) {
        this.jerseyService = jerseyService;
    }

    @GetMapping
    public ResponseEntity<List<JerseyResponse>> getAll() {
        List<JerseyResponse> body = jerseyService.getAll()
                .stream()
                .map(JerseyMapper::toResponse)
                .toList();
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JerseyResponse> getOne(@PathVariable Long id) {
        var jersey = jerseyService.getById(id);
        JerseyResponse body = JerseyMapper.toResponse(jersey);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<JerseyResponse> create(@RequestBody JerseyRequest request) {
        var saved = jerseyService.create(request);
        JerseyResponse body = JerseyMapper.toResponse(saved);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).body(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JerseyResponse> update(@PathVariable Long id,
                                                 @RequestBody JerseyRequest request) {
        var updated = jerseyService.update(id, request);
        JerseyResponse body = JerseyMapper.toResponse(updated);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        jerseyService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
