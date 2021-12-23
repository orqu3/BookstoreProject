package com.bookstore.common.entity.order;

import com.bookstore.common.entity.IdBasedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Transient
    public String getUpdatedTimeOnForm(){
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        return dateFormatter.format(this.updateTime);
    }

    public void setUpdateTimeOnForm(String dateString){
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");

        try{
            this.updateTime = dateFormatter.parse(dateString);
        }catch (ParseException ex){
            ex.printStackTrace();
        }
    }
}
