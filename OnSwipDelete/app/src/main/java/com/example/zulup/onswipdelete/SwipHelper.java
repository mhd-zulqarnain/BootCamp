package com.example.zulup.onswipdelete;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by zulup on 1/27/2018.
 */

public class SwipHelper extends ItemTouchHelper.SimpleCallback {
    MovieAdapter adapter;
    public SwipHelper(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    public SwipHelper(MovieAdapter adapter){
        super(ItemTouchHelper.UP |ItemTouchHelper.DOWN ,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter=adapter;
    }
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.removeItem(viewHolder.getAdapterPosition());
    }
}
