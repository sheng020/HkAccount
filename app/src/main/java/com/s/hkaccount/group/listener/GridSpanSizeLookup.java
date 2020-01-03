package com.s.hkaccount.group.listener;

import androidx.recyclerview.widget.GridLayoutManager;

/**
 * @author: limuyang
 * @date: 2019-12-03
 * @Description:
 */
public interface GridSpanSizeLookup {

    int getSpanSize(GridLayoutManager gridLayoutManager, int viewType, int position);
}
