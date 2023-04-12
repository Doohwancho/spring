package com.kata.orderinhexagonal.delivery.adapter.out.persistence;

import com.kata.orderinhexagonal.delivery.domain.DeliveryStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus status;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDateTime;

    public DeliveryEntity(Long id, DeliveryStatus status, String location, LocalDateTime createdDateTime) {
        this.id = id;
        this.status = status;
        this.location = location;
        this.createdDateTime = createdDateTime;
    }
}
