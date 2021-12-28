package com.chus.clua.lorempicsum.presentation.binding

interface ItemClickHandler<T> {
    fun onItemClicked(item: T)
}