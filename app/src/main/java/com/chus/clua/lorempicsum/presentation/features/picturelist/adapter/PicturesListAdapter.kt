package com.chus.clua.lorempicsum.presentation.features.picturelist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.chus.clua.lorempicsum.databinding.ItemPictureBinding
import com.chus.clua.lorempicsum.presentation.binding.ItemClickHandler
import com.chus.clua.lorempicsum.presentation.models.PictureUiModel

class PicturesListAdapter(
    private val handler: ItemClickHandler<PictureUiModel>
) : PagingDataAdapter<PictureUiModel, PictureViewHolder>(PicturesComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        return PictureViewHolder(
            ItemPictureBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), handler
        )
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        getItem(position)?.let { item -> holder.bind(item) }
    }

}