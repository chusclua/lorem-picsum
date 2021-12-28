package com.chus.clua.lorempicsum.presentation.features.picturelist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.chus.clua.lorempicsum.databinding.ActivityPictureListBinding
import com.chus.clua.lorempicsum.presentation.binding.ItemClickHandler
import com.chus.clua.lorempicsum.presentation.binding.RetryButtonHandler
import com.chus.clua.lorempicsum.presentation.features.detail.PictureDetailActivity
import com.chus.clua.lorempicsum.presentation.features.picturelist.adapter.PicturesListAdapter
import com.chus.clua.lorempicsum.presentation.features.picturelist.adapter.PicturesLoadStateAdapter
import com.chus.clua.lorempicsum.presentation.models.PictureUiModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel


class PictureListActivity : AppCompatActivity(), ItemClickHandler<PictureUiModel>, RetryButtonHandler {

    private val viewModel: PictureListViewModel by viewModel()
    private val picturesListAdapter: PicturesListAdapter by lazy { PicturesListAdapter(this) }
    private lateinit var binding: ActivityPictureListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPictureListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenCreated  {
            viewModel.picturesFlow.collectLatest {
                picturesListAdapter.submitData(it)
            }
        }
    }

    override fun onItemClicked(item: PictureUiModel) {
        item.id?.let { pictureID -> openPictureDetailActivity(pictureID) }
    }

    override fun onRetryButtonClicked() {
        picturesListAdapter.retry()
    }

    private fun openPictureDetailActivity(pictureId: Int) {
        PictureDetailActivity.start(this, pictureId)
    }

    private fun initRecyclerView() {
        binding.rvPictures.apply {
            adapter = picturesListAdapter.withLoadStateFooter(
                footer = PicturesLoadStateAdapter(this@PictureListActivity)
            )
        }

    }
}
