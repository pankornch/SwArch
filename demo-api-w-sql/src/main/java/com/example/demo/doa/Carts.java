package com.example.demo.doa;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Carts {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;
    private Date cartCreateDate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "carts")

    private List<Products> products;


    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public Date getCartCreateDate() {
        return cartCreateDate;
    }

    public void setCartCreateDate(Date cartCreateDate) {
        this.cartCreateDate = cartCreateDate;
    }

    @JsonManagedReference
    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }
}
