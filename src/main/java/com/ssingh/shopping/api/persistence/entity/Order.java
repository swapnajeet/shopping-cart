package com.ssingh.shopping.api.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "order_status")
    private String orderStatus;
    @Column(name = "total_price")
    private Double totalPrice;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
    @Column(name = "user_id")
    private String userId;
}
