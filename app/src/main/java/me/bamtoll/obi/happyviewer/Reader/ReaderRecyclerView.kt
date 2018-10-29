package me.bamtoll.obi.happyviewer.Reader

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log

class ReaderRecyclerView(context: Context): RecyclerView(context) {

    override fun fling(velocityX: Int, velocityY: Int): Boolean {

        Log.d("SPEED", velocityY.toString())

        return super.fling(velocityX, velocityY)
    }

}