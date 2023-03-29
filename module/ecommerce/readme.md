---\
Goal


modularize ecommerce




---\
Concept


c-a. user-product-order
1. account :white_check_mark:
	1. send email when user registered :white_check_mark:
	2. CapturedOutput to capture log to test :white_check_mark:
2. product :white_check_mark:
	1. CaffeineCache library :white_check_mark:
	2. cache test 코드 작성 :white_check_mark:
3. order :white_check_mark:
4. archunit for dependency test :white_check_mark:
	1. test for no cyclinic dependencies between packages :white_check_mark:


c-b. order-in-hexagonal
1. 회원
	1. password encoding using commons-codec lib :white_check_mark:
	2. member join & find in hexagonal way :white_check_mark:
2. 상품
3. 주문
4. 할인
5. 결제
6. 재고
7. 배송





---\
Reference


c-a. [user-product-order by ejoongseok](https://github.com/ejoongseok/app-kata)

c-b. [order-in-hexagonal](https://github.com/ejoongseok/order-in-hexagonal)
