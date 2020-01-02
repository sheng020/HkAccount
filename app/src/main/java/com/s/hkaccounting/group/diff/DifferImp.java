package com.s.hkaccounting.group.diff;

/**
 * 使用java接口定义方法
 * @param <T>
 */
public interface DifferImp<T> {
    void addListListener(ListChangeListener<T> listChangeListener);
}
