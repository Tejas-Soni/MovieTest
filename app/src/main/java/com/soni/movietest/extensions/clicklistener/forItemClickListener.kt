package com.soni.movietest.extensions.clicklistener



/**
 * Created By Tejas Soni
 * tejashsoni51331@gmail.com
 */
interface forItemClickListener<T> {
    fun forItem( item: T,position: Int=0, any: Any = Any())
}

inline fun <T> forItemClickListenerDSL(crossinline callback: (item: T,position: Int,  any: Any) -> Unit = { _, _, _ -> }) =
        object : forItemClickListener<T> {
            override fun forItem(item: T,position: Int,  any: Any) {
                callback(item, position, any)
            }
        }

