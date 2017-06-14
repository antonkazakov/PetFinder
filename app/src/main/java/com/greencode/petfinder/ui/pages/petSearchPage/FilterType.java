package com.greencode.petfinder.ui.pages.petSearchPage;

/**
 * @author Anton Kazakov
 * @date 13.06.17.
 */

public enum FilterType {

    SIZE("size"),
    ANIMAL("animal"),
    SEX("sex"),
    AGE("age");

    private String type;

    FilterType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
