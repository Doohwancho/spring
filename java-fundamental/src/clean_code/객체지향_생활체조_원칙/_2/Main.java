package clean_code.객체지향_생활체조_원칙._2;

public class Main {
    //case1) if-else hell
    public String getAgeCategory(int age) {
        if (age < 5) {
            return "infant";
        } else {
            if (age < 12) {
                return "child";
            } else {
                if (age < 19) {
                    return "teenager";
                } else {
                    return "adult";
                }
            }
        }
    }


    //case2) early return. if문 안에 null 거르는 조건문 넣을 수도 있고, if문 대신 switch문 쓸 수도 있다.
    public String getAgeCategory2(int age) {
        if (age < 5) {
            return "infant";
        }
        if (age < 12) {
            return "child";
        }
        if (age < 19) {
            return "teenager";
        }
        return "adult";
    }
}
