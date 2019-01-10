package com.example.company.onetrak;

public interface BasePresenter<T> {

    void takeView(T view);

    void dropView();

}
