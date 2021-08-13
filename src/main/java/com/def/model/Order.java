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
@Table(name = "ord")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double finalCost;
    private int quantity;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "prod_in_ord",
            joinColumns = {
                    @JoinColumn(name = "ord_id")
            },inverseJoinColumns = {
            @JoinColumn(name = "prod_id")
    })
    private Set<Product> products = new HashSet<>();


    public void addFinalCost() {
        for (Product product : products) {
            this.finalCost += product.getCost();
        }
    }

    public void addQuantity() {
        this.quantity = products.size();
    }

    public void addProduct(Product product){
        products.add(product);
    }

    @Override
    public String toString() {
        return String.format("id: %d Quantity: %d Final cost: %.2f ", id, quantity, finalCost);
    }
}
