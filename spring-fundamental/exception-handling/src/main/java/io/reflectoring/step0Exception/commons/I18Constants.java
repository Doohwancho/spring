package io.reflectoring.step0Exception.commons;


import lombok.Getter;

@Getter
public enum I18Constants {
    NO_ITEM_FOUND("item.absent"); //TODO - Q. I18constant 처리 하는 이유

    String key;
    I18Constants(String key) {
        this.key = key;
    }
}