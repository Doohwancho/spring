---\
A. architecture
[architecture](https://i.stack.imgur.com/0xqKt.png)

1. Red = model
2. Green = view
3. Blue = controller

의존성의 방향이

view -> controller -> model


model은 controller를 몰라도 되고,
controller는 view를 몰라도 된다.


---\
B. logic flow

[logic flow](https://link-intersystems.com/wp-content/uploads/2014/03/MVCUIexamplesequence.png)


---\
C. 분석


view에서 button 이나 텍스트 넣는 창에 Action을 event-listener로 등록해 준다.\
Action들은 model에 의존하는데, view단에서 사용자가 버튼 클릭해서 event 쏘면,\
controller단에 ~Action에서 actionPerformed()를 수행한다.\
actionPerformed()에는 model의 값을 바꾸는 로직이 있다.



---\
reference


1. https://stackoverflow.com/questions/5217611/the-mvc-pattern-and-swing
2. https://link-intersystems.com/blog/2013/07/20/the-mvc-pattern-implemented-with-java-swing/
