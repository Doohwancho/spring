package template_method;

public abstract class HouseTemplate {

    public final void buildHouse() {
        buildFoundation();
        buildPillars();
        buildWalls();
        buildWindows();
        System.out.println("House is built");
    }

    private void buildWindows() {
        System.out.println("Building Glass Windows");
    }

    public abstract void buildWalls();
    public abstract void buildPillars();

    private void buildFoundation(){
        System.out.println("Building foundation with cement,iron rods and sand");
    }
}

/*

buildHouse()에 정해진 템플릿이 있고,
이 추상클래스를 상속받는 GlassHouse, WoodenHouse는 
정해진 템플릿 속에서 바꿔야 하는 부분만 입맛 따라 바꾼다.

정해진 틀을 만들고찍어내는 방

*/