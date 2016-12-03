package com.dengpf.bean;

/**
 * Created by kobe73er on 16/10/29.
 */
public abstract class AAnimal implements IAnimal {

    protected String kind;
    protected int age;

    public AAnimal(String kind, int age) {
        this.kind = kind;
        this.age = age;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "AAnimal{" +
                "kind='" + kind + '\'' +
                ", age=" + age +
                '}';
    }
}
