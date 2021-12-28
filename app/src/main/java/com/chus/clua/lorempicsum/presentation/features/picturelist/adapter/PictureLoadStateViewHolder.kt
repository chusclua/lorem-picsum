package com.chus.clua.lorempicsum.presentation.features.picturelist.adapter

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.chus.clua.lorempicsum.databinding.ItemLoadingStateBinding
import com.chus.clua.lorempicsum.presentation.binding.RetryButtonHandler

class PictureLoadStateViewHolder(
    private val binding: ItemLoadingStateBinding,
    private val handler: RetryButtonHandler
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState) {
        binding.handler = handler
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.btRetry.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error
        if (loadState is LoadState.Error) binding.errorMsg.text = loadState.error.localizedMessage
    }
}