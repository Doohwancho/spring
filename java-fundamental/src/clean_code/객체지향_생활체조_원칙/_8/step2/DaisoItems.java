package clean_code.객체지향_생활체조_원칙._8.step2;

import java.util.ArrayList;
import java.util.List;

public class DaisoItems {

    //case2) custom wrapper class안에 유효성 검사 처리한 방법(.addItem()안에)

//    private final List<Item> items;
//
//    public DaisoItems() {
//        this.items = new ArrayList<>();
//    }
//
//    public void addItem(final Item item) {
//        if (item.isPriceHigherThan(5000)) {
//            throw new IllegalArgumentException("5000원이 넘는 물건은 진열할 수 없습니다.");
//        }
//
//        items.add(item);
//    }
}