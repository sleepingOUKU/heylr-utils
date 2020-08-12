package com.heylr.entity;


import java.util.Objects;

/**
 * 二元组实体类，first与second定义为二元组的两个变量
 * @author zhangyunlin
 * @param <T>
 */
public class Tuples<T> {

    private T first;

    private T second;

    public Tuples(){ }

    public Tuples(T first,T second){
        this.first = first;
        this.second = second;
    }

    public T getFirst(){
        return first;
    }

    public T getSecond(){
        return second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "Tuples{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuples<?> tuples = (Tuples<?>) o;
        return Objects.equals(first, tuples.first) &&
                Objects.equals(second, tuples.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
