package org.example.jpashop.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.jpashop.domain.OrderStatus;

@Getter @Setter
public class OrderSearch {

    private String memberName;

    private OrderStatus orderStatus;
}
