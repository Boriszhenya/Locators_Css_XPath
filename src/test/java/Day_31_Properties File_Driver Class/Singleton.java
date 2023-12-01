package com.eurotechstudy.sdet;

public class Singleton {

    //Синглтон содержит приватный конструктор, что препятсвует созданию нового экземпляра класса
    private Singleton() {
    }

    private static Singleton instance;

    public static Singleton getInstance() {
        if (instance == null) {
            System.out.println("Инициализация синглтона");
            instance = new Singleton();
        }

        return instance;
    }
}
