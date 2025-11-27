package com.champsoft.jerserycrazedatabase.presentation.mapper;

import com.champsoft.jerserycrazedatabase.dataaccess.entity.Jersey;
import com.champsoft.jerserycrazedatabase.presentation.dto.Jersey.JerseyRequest;
import com.champsoft.jerserycrazedatabase.presentation.dto.Jersey.JerseyResponse;

public class JerseyMapper {

    public static Jersey toEntity(JerseyRequest request) {
        Jersey jersey = new Jersey();
        jersey.setName(request.name());
        jersey.setClub(request.club());
        jersey.setType(request.type());
        jersey.setSize(request.size());
        jersey.setPrice(request.price());
        jersey.setInStock(request.inStock());
        jersey.setDescription(request.description());
        jersey.setImageLink(request.imageLink());
        return jersey;
    }

    public static void updateEntity(Jersey jersey, JerseyRequest request) {
        jersey.setName(request.name());
        jersey.setClub(request.club());
        jersey.setType(request.type());
        jersey.setSize(request.size());
        jersey.setPrice(request.price());
        jersey.setInStock(request.inStock());
        jersey.setDescription(request.description());
        jersey.setImageLink(request.imageLink());
    }

    public static JerseyResponse toResponse(Jersey jersey) {
        return new JerseyResponse(
                jersey.getId(),
                jersey.getName(),
                jersey.getClub(),
                jersey.getType(),
                jersey.getSize(),
                jersey.getPrice(),
                jersey.getInStock(),
                jersey.getDescription(),
                jersey.getImageLink()
        );
    }


}
