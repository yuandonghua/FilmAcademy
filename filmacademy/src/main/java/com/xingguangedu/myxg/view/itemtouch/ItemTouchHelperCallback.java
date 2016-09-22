package com.xingguangedu.myxg.view.itemtouch;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.xingguangedu.myxg.listener.OnMoveAndSwipedListener;

/**
 * @description:重写ItemTouchHelper.Callback
 * @author:袁东华 created at 2016/8/30 0030 上午 11:05
 */
public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private OnMoveAndSwipedListener adapter;

    public ItemTouchHelperCallback(OnMoveAndSwipedListener onMoveAndSwipedListener) {
        this.adapter = onMoveAndSwipedListener;
    }

    /**
     * Should return a composite flag which defines the enabled move directions in each state
     * (idle, swiping, dragging).
     * <p>
     * Instead of composing this flag manually, you can use {@link #makeMovementFlags(int,
     * int)}
     * or {@link #makeFlag(int, int)}.
     * <p>
     * This flag is composed of 3 sets of 8 bits, where first 8 bits are for IDLE state, next
     * 8 bits are for SWIPE state and third 8 bits are for DRAG state.
     * Each 8 bit sections can be constructed by simply OR'ing direction flags defined in
     * {@link ItemTouchHelper}.
     * <p>
     * For example, if you want it to allow swiping LEFT and RIGHT but only allow starting to
     * swipe by swiping RIGHT, you can return:
     * <pre>
     *      makeFlag(ACTION_STATE_IDLE, RIGHT) | makeFlag(ACTION_STATE_SWIPE, LEFT | RIGHT);
     * </pre>
     * This means, allow right movement while IDLE and allow right and left movement while
     * swiping.
     *
     * @param recyclerView The RecyclerView to which ItemTouchHelper is attached.
     * @param viewHolder   The ViewHolder for which the movement information is necessary.
     * @return flags specifying which movements are allowed on this ViewHolder.
     * @see #makeMovementFlags(int, int)
     * @see #makeFlag(int, int)
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //这个方法是用来设置条目拖动方向和侧滑方向的
        int dragFlags = 0, swipeFlags = 0;
        //LinearLayout样式
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            //设置拖拽方向为上下
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            //设置侧滑方向为左右
            swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        } else if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            //GridLayout样式
            //设置拖拽方向为上下
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            //不支持侧滑
            swipeFlags = 0;
        }

        return makeMovementFlags(dragFlags, swipeFlags);
    }

    /**
     * Called when ItemTouchHelper wants to move the dragged item from its old position to
     * the new position.
     * <p>
     * If this method returns true, ItemTouchHelper assumes {@code viewHolder} has been moved
     * to the adapter position of {@code target} ViewHolder
     * ({@link ViewHolder#getAdapterPosition()
     * ViewHolder#getAdapterPosition()}).
     * <p>
     * If you don't support drag & drop, this method will never be called.
     *
     * @param recyclerView The RecyclerView to which ItemTouchHelper is attached to.
     * @param viewHolder   The ViewHolder which is being dragged by the user.
     * @param target       The ViewHolder over which the currently active item is being
     *                     dragged.
     * @return True if the {@code viewHolder} has been moved to the adapter position of
     * {@code target}.
     * @see #onMoved(RecyclerView, ViewHolder, int, ViewHolder, int, int, int)
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //拖拽item时会调用此方法
        //如果两个条目不是同一类型,不可拖拽
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }
        //回调adapter中的onItemMove方法,更新数据
        adapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return false;
    }

    /**
     * Called when a ViewHolder is swiped by the user.
     * <p>
     * If you are returning relative directions ({@link #START} , {@link #END}) from the
     * {@link #getMovementFlags(RecyclerView, ViewHolder)} method, this method
     * will also use relative directions. Otherwise, it will use absolute directions.
     * <p>
     * If you don't support swiping, this method will never be called.
     * <p>
     * ItemTouchHelper will keep a reference to the View until it is detached from
     * RecyclerView.
     * As soon as it is detached, ItemTouchHelper will call
     * {@link #clearView(RecyclerView, ViewHolder)}.
     *
     * @param viewHolder The ViewHolder which has been swiped by the user.
     * @param direction  The direction to which the ViewHolder is swiped. It is one of
     *                   {@link #UP}, {@link #DOWN},
     *                   {@link #LEFT} or {@link #RIGHT}. If your
     *                   {@link #getMovementFlags(RecyclerView, ViewHolder)}
     *                   method
     *                   returned relative flags instead of {@link #LEFT} / {@link #RIGHT};
     *                   `direction` will be relative as well. ({@link #START} or {@link
     *                   #END}).
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //侧滑item时会调用此方法
        //回调adapter中的onItemDelete方法,更新数据
        adapter.onItemDelete(viewHolder.getAdapterPosition());
    }
}
