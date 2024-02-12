package com.example.ayrotekchallange.mapper;

import com.example.ayrotekchallange.domain.Client;
import com.example.ayrotekchallange.dto.ClientDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientDTOMapper extends BaseDTOMapper<Client, ClientDTO> {
}
