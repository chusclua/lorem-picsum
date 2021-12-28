package com.chus.clua.lorempicsum.presentation.features.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chus.clua.lorempicsum.databinding.ActivityPictureDetailBinding
import com.chus.clua.lorempicsum.presentation.binding.BackButtonHandler
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PictureDetailActivity : AppCompatActivity(), BackButtonHandler {

    private lateinit var binding: ActivityPictureDetailBinding
    private val viewModel: PictureDetailViewModel by viewModel {
        parametersOf(intent.getIntExtra(PICTURE_UI_MODEL, -1))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPictureDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.handler = this
        binding.viewModel = viewModel

    }

    override fun onBackButtonClicked() {
        onBackPressed()
    }

    companion object {
        private const val PICTURE_UI_MODEL = "picture_ui_model_id"
        fun start(context: Activity, pictureId: Int) {
            Intent(context, PictureDetailActivity::class.java).apply {
                putExtra(PICTURE_UI_MODEL, pictureId)
            }.run { context.startActivity(this) }
        }
    }

}
