package com.example.giangdam.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Giang.Dam on 9/4/2015.
 */
public class MyRecyclerViewItemClickListener implements RecyclerView.OnItemTouchListener {


   public interface MyOnItemClickListener{
       public void onItemClick(View view, int position);
   }

    MyOnItemClickListener mListener;  // this variable is control item click event of recycler view.
    GestureDetector mGestureDetector; //detect gesture when interact with recycler view.


    //constructor
    public MyRecyclerViewItemClickListener(Context context, MyOnItemClickListener listener){
        this.mListener = listener;
        this.mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }


    //importan method need caring
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        View chidView = rv.findChildViewUnder(e.getX(),e.getY());
        if(chidView != null && mListener != null && mGestureDetector.onTouchEvent(e)){
            mListener.onItemClick(chidView,rv.getChildAdapterPosition(chidView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
