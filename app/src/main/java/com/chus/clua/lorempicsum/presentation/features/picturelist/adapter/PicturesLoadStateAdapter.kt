package com.chus.clua.lorempicsum.presentation.features.picturelist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.chus.clua.lorempicsum.databinding.ItemLoadingStateBinding
import com.chus.clua.lorempicsum.presentation.binding.RetryButtonHandler

class PicturesLoadStateAdapter(
    private val handler: RetryButtonHandler
) : LoadStateAdapter<PictureLoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PictureLoadStateViewHolder =
        PictureLoadStateViewHolder(
            ItemLoadingStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), handler
        )

    override fun onBindViewHolder(holder: PictureLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

}