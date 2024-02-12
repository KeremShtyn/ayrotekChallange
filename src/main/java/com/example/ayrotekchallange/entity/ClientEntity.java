package com.example.ayrotekchallange.entity;


import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Table(name = "Client", indexes = {@Index(columnList = "id", name = "client_id_indx")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@Entity(name = "Client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    @OneToMany(mappedBy = "client", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<ProductEntity> products;

    public ClientEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientEntity)) return false;
        ClientEntity client = (ClientEntity) o;
        return Objects.equals(id, client.id) && Objects.equals(name, client.name) && Objects.equals(products, client.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, products);
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
