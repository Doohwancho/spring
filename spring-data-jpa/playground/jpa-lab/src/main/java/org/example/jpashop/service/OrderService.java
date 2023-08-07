package org.example.jpashop.service;

import lombok.RequiredArgsConstructor;
import org.example.jpashop.controller.OrderController.OrderSearch;
import org.example.jpashop.domain.Delivery;
import org.example.jpashop.domain.Member;
import org.example.jpashop.domain.Order;
import org.example.jpashop.domain.OrderItem;
import org.example.jpashop.domain.item.Item;
import org.example.jpashop.repository.jpql.ItemRepository;
import org.example.jpashop.repository.jpql.OrderRepository;
import org.example.jpashop.repository.queryDSL.MemberJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final MemberJpaRepository memberRepository;

    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());


        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {

        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    /**
     * 주문 검색
     */
    public List<Order> findAllByString(OrderSearch orderSearch) {
        return orderRepository.findAllByString(orderSearch);
    }

    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByCriteria(orderSearch);
    }
}
