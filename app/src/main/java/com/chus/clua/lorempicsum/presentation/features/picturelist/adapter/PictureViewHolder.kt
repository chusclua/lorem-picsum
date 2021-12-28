package com.chus.clua.lorempicsum.presentation.features.picturelist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.chus.clua.lorempicsum.databinding.ItemPictureBinding
import com.chus.clua.lorempicsum.presentation.binding.ItemClickHandler
import com.chus.clua.lorempicsum.presentation.models.PictureUiModel


class PictureViewHolder(
    private val binding: ItemPictureBinding,
    private val handler: ItemClickHandler<PictureUiModel>
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PictureUiModel) {
        binding.item = item
        binding.handler = handler
    }
}