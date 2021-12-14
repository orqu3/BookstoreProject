package com.bookstore.common.entity.product;

import com.bookstore.common.entity.Category;
import com.bookstore.common.entity.IdBasedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends IdBasedEntity {

    @Column(unique = true, length = 256, nullable = false)
    private String name;

    @Column(unique = true, length = 256, nullable = false)
    private String alias;

    @Column(unique = true, length = 256, nullable = false)
    private String author;

    @Column(length = 1024, nullable = false)
    private String description;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "updated_time")
    private Date updatedTime;

    private Boolean enabled;

    @Column(name = "in_stock")
    private Boolean inStock;

    private float cost;

    private float price;

    @Column(name = "discount_percent")
    private float discountPercent;

    private float weight;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductDetail> details = new ArrayList<>();

    public void addDetail(String name, String value) {
        this.details.add(new ProductDetail(name, value, this));
    }

    public void addDetail(Integer id, String name, String value) {
        this.details.add(new ProductDetail(id, name, value, this));
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Transient
    public float getDiscountPrice() {
        if (discountPercent > 0) {
            return price * ((100 - discountPercent) / 100);

        }
        return this.price;
    }
}
