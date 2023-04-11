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
0. goal
	1. familiarized with hexagonal architecture
	2. requirements -> erd -> entity modeling (especially order-orderItem-item part)
	3. complex eommerce business requirement logics to java code
1. database modeling
	1. requirements :white_check_mark:
	2. erd (using vuerd tool from VSC) :white_check_mark:
	3. entity-modeling :white_check_mark:
2. member:white_check_mark:
	1. password encoding using commons-codec lib :white_check_mark:
	2. member join & find in hexagonal way :white_check_mark:
	3. email, password validator :white_check_mark:
3. item :white_check_mark:
	1. item validator :white_check_mark:
4. stock :white_check_mark:
	1. stock validator :white_check_mark:
5. discount :white_check_mark:
	1. discount validator :white_check_mark:
6. order
	1. discount가 item의 erd, table에 안 속해있어도, join 때문에 필요하면 추가해놓는다. :white_check_mark:
	2. order시에 stockOut을 async queue로 처리 :white_check_mark:
	3. OrderService에서 MemberService를 바로 주입받지 말고 port타고 나가서 MemberRepository 받아 해결 :white_check_mark:
	4. OrderService에서 item.stockOut() 해서 domain을 stockService에 의존시키지 말고, port로 의존성 돌리자 :white_check_mark:
	5. DI 필드에 final을 붙이는 이유 :white_check_mark:
	6. refactor - discounted price of item using switch :white_check_mark:
	7. order domain -> entity 변환 시, 그 안에 orderItem도 entity로 변환해야 한다 :white_check_mark:
	8. order 저장 후, entity에서 가져온 id를 domain order에 넣음과 동시에 orderItem에도 넣어줘야 한다. :white_check_mark:
	9. order을 jpa repository로 찾을 때, member를 fetch join하여 찾는다 :white_check_mark:
	10. orderItem을 repository로 찾을 때, item과 order를 fetch join하여 찾는다. :white_check_mark:
	11. orderItem이 erd상에는 order에 속해있지 않지만, 필요하여 domain에 껴있으니, order를 찾은 후, orderItem도 찾아 더해준다. :white_check_mark:
	12. cancel order은 soft delete for later customer behavior analysis for market :white_check_mark:
7. payment
8. delivery





---\
Reference


c-a. [user-product-order by ejoongseok](https://github.com/ejoongseok/app-kata)

c-b. [order-in-hexagonal](https://github.com/ejoongseok/order-in-hexagonal)
