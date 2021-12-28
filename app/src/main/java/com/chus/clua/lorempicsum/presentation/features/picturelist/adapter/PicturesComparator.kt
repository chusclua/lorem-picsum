package com.chus.clua.lorempicsum.presentation.features.picturelist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.chus.clua.lorempicsum.presentation.models.PictureUiModel


object PicturesComparator : DiffUtil.ItemCallback<PictureUiModel>() {

    override fun areItemsTheSame(oldItem: PictureUiModel, newItem: PictureUiModel) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: PictureUiModel, newItem: PictureUiModel) =
        oldItem.id == newItem.id

}