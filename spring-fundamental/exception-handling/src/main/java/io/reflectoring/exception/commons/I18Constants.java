package io.reflectoring.exception.commons;


import com.sun.xml.bind.v2.TODO;
import lombok.Getter;

@Getter
public enum I18Constants {
    NO_ITEM_FOUND("item.absent"); //TODO - Q. I18constant 처리 하는 이유

    String key;
    I18Constants(String key) {
        this.key = key;
    }
}