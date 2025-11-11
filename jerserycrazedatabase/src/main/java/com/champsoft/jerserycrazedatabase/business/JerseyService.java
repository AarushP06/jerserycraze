package com.champsoft.jerserycrazedatabase.business;
import com.champsoft.jerserycrazedatabase.dataaccess.entity.Jersey;
import com.champsoft.jerserycrazedatabase.dataaccess.repository.JerseyRepository;
import com.champsoft.jerserycrazedatabase.exceptions_utilities.EntityNotFoundException;
import com.champsoft.jerserycrazedatabase.presentation.dto.Jersey.JerseyRequest;
import com.champsoft.jerserycrazedatabase.presentation.mapper.JerseyMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JerseyService {

    private final JerseyRepository jerseyRepository;

    public JerseyService(JerseyRepository jerseyRepository) {
        this.jerseyRepository = jerseyRepository;
    }

    public List<Jersey> getAll() {
        return jerseyRepository.findAll();
    }

    public Jersey getById(Long id) {
        return jerseyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jersey " + id + " not found"));
    }

    public Jersey create(JerseyRequest request) {
        Jersey jersey = JerseyMapper.toEntity(request);
        return jerseyRepository.save(jersey);
    }

    public Jersey update(Long id, JerseyRequest request) {
        Jersey jersey = getById(id);
        JerseyMapper.updateEntity(jersey, request);
        return jerseyRepository.save(jersey);
    }

    public void delete(Long id) {
        if (!jerseyRepository.existsById(id)) {
            throw new EntityNotFoundException("Jersey " + id + " not found");
        }
        jerseyRepository.deleteById(id);
    }
}