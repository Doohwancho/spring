package org.example.jpashop.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.example.jpashop.domain.Address;
import org.example.jpashop.domain.Member;
import org.example.jpashop.domain.Order;
import org.example.jpashop.domain.OrderStatus;
import org.example.jpashop.domain.item.Book;
import org.example.jpashop.domain.item.Item;
import org.example.jpashop.exception.NotEnoughStockException;
import org.example.jpashop.repository.jpql.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
@Transactional
public class OrderServiceTest {
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private OrderService orderService;
    
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Test
    public void 상품주문() {
        //given
        Member member = createMember("회원1", "서울", "테헤란로", "12345");
        
        Item book = createBook("시골 JPA", 10000, 10);
        
        int orderCount = 2;
        
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        
        
        //then
        Order getOrder = orderRepository.findOne(orderId);
        
        assertEquals(OrderStatus.ORDER, getOrder.getStatus()); //상품 주문 시 상태는 ORDER이다.
        assertEquals(1, getOrder.getOrderItems().size()); //주문한 상품 종류 수가 정확해야 한다.
        assertEquals(10000 * orderCount, getOrder.getTotalPrice()); //주문 가격은 가격 * 수량이다.
        assertEquals(8, book.getStockQuantity()); //주문 수량만큼 재고가 줄어야 한다.
    }
    
    @Test
    public void 상품주문_재고수량초과() {
        //given
        Member member = createMember("회원1", "서울", "테헤란로", "12345");
        Item item = createBook("시골 JPA", 10000, 10);
        
        int orderCount = 11;
        
        //when
        Assertions.assertThrows(NotEnoughStockException.class, () -> {
            orderService.order(member.getId(), item.getId(), orderCount); //재고 수량 부족 예외가 발행해야 한다.
        });
    }
    
    @Test
    public void 주문취소() {
        //given
        Member member = createMember("회원1", "서울", "테헤란로", "12345");
        Item item = createBook("시골 JPA", 10000, 10);
        
        int orderCount = 2;
        
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        
        //when
        orderService.cancelOrder(orderId);
        
        //then
        Order getOrder = orderRepository.findOne(orderId);
        
        assertEquals(OrderStatus.CANCEL, getOrder.getStatus()); //"주문 취소 시 상태는 CANCEL이다."
        assertEquals(10, item.getStockQuantity()); //"주문이 취소된 상품은 그만큼 재고가 증가해야 한다."
    }
    
    private Item createBook(String name, int price, int stockQuantity) {
        Item book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book); //em.persist(book);은 jpa 1st layer cache엔 들고있지만, transaction이 끝날 때 까지 db에 INSERT query 날리진 않는다.
        return book;
    }
    
    private Member createMember(String name, String city, String street, String zipcode) {
        Member member = new Member();
        member.setUserName(name);
        member.setAddress(new Address(city, street, zipcode));
        em.persist(member); //em.persist(member);은 jpa 1st layer cache엔 들고있지만, transaction이 끝날 때 까지 db에 INSERT query 날리진 않는다.
        return member;
    }
}