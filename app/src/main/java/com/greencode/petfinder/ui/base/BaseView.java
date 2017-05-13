package com.greencode.petfinder.ui.base;

/**
 * @author Anton Kazakov
 * @date 01.04.17.
 */

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T t);

    void showError(String text);

    void showLoading(boolean isLoading);

}
