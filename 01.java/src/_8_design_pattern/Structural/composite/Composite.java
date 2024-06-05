package _8_design_pattern.Structural.composite;

import java.util.ArrayList;

public class Composite extends Equipment {

    ArrayList<Equipment> equipments = new ArrayList<>(); //여기서 컴퓨터 장비 일괄 관리 (tree -> linear)

    Composite(String name) {
        super(0, name);
    }

    @Override
    public int getPrice() {
        return equipments.stream()
                .map(Equipment::getPrice)
                .mapToInt(p -> (int) p)
                .sum();
    }

    public void add(Equipment equipment) {
        equipments.add(equipment);
    }
}
