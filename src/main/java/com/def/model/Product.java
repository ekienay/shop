package com.def.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "prod")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private double cost;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "prod_in_ord",
            joinColumns = {
                    @JoinColumn(name = "prod_id")
            },inverseJoinColumns = {
            @JoinColumn(name = "ord_id")
    })
    private Set<Order> orders = new HashSet<>();

    @Override
    public String toString() {
        return "id: "+ id + " " +
                "name: " + name +
                " " +
                "cost:" + cost;
    }
}


