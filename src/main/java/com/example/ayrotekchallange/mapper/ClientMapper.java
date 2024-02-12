package com.example.ayrotekchallange.mapper;


import com.example.ayrotekchallange.domain.Client;
import com.example.ayrotekchallange.entity.ClientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper extends BaseMapper<ClientEntity, Client> {

}
