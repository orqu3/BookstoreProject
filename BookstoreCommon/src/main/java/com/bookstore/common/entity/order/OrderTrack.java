package com.bookstore.common.entity.order;

import com.bookstore.common.entity.IdBasedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "order_track")
public class OrderTrack extends IdBasedEntity {

    @Column(length = 255)
    private String notes;

    private Date updateTime;

    @Enumerated(EnumType.STRING)
    @Column(length = 45, nullable = false)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
