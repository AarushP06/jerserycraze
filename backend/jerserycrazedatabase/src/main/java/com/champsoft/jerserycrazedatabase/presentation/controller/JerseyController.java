package com.champsoft.jerserycrazedatabase.presentation.controller;
import com.champsoft.jerserycrazedatabase.dataaccess.entity.Jersey;
import com.champsoft.jerserycrazedatabase.dataaccess.repository.JerseyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jerseys")
public class JerseyController {

    private final JerseyRepository repo;
    public JerseyController(JerseyRepository repo) { this.repo = repo; }

    @GetMapping
    public Page<Jersey> list(
            @RequestParam(required = false) String q,
            Pageable pageable
    ) {
        Pageable sorted = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("id").ascending()
        );

        if (q == null || q.isBlank()) {
            return repo.findAll(sorted);
        }

        String s = q.trim();
        return repo.findByNameContainingIgnoreCaseOrClubContainingIgnoreCaseOrderByIdAsc(
                s, s, sorted
        );
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
