package com.champsoft.jerserycrazedatabase.presentation.controller;
import com.champsoft.jerserycrazedatabase.dataaccess.entity.Jersey;
import com.champsoft.jerserycrazedatabase.dataaccess.repository.JerseyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jerseys")
public class JerseyController {

    private final JerseyRepository repo;
    public JerseyController(JerseyRepository repo) { this.repo = repo; }

    @GetMapping
    public Page<Jersey> list(@RequestParam(required = false) String q, Pageable pageable) {
        if (q == null || q.isBlank()) return repo.findAll(pageable);
        String s = q.trim();
        return repo.findByNameContainingIgnoreCaseOrClubContainingIgnoreCase(s, s, pageable);
    }

    @GetMapping("/{id}")
    public Jersey get(@PathVariable Long id) { return repo.findById(id).orElseThrow(); }

    @PostMapping
    public Jersey create(@RequestBody Jersey j) { return repo.save(j); }

    @PutMapping("/{id}")
    public Jersey update(@PathVariable Long id, @RequestBody Jersey j) {
        j.setId(id);
        return repo.save(j);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { repo.deleteById(id); }
}
