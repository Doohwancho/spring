package clean_code._01_principle_._01_이해하기_쉬운_코드.etc.객체지향_생활체조_원칙._4;

public class Main {
    public static void main(String[] args) {
        //case1) 점이 여러개인 경우
//        if (person.getMoney().getValue() > 10000) {
//            System.out.println("당신은 부자 💰");
//        }

        //case2) 점이 한개인 경우
//        if (person.hasMoneyMoreThan(10000)) {
//            System.out.println("당신은 부자 💰");
//        }
    }
}

/*

---
내 생각

물론 .hasMoneyMoreThan()이 .getMoney().getValue() 보다 더 직관적일 순 있어. 캡슐화도 되고.
근데 코드 더 써야하잖아?

.getMoney().getValue() 여기에 점이 몇개 더 덕지덕지 붙어서 초딩이 봤을 때 이해하기 어려우면, 메서드 따로 만들 순 있지.
근데 저정도 가지고 메서드를 별개로 만든다?
코드 수도 길어지고 atomic 하지 않잖아.


아, tell don't ask 원칙에도 어느정도 맞긴 하는데,
장단점이 있네.

장단점이 있으면 원칙이라고 부르면 안되지 않나?
방법론이라고 불러야지


 */
