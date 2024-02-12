package com.example.ayrotekchallange.mapper;

import java.util.List;

public interface BaseDTOMapper<Domain, DTO> {

    Domain toDomain(DTO dto);

    List<DTO> toListDTO(List<Domain> domainObjects);

    DTO toDTO(Domain domain);

    List<Domain> toDomainList(List<DTO> dtos);

}
