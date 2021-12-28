package com.chus.clua.lorempicsum.domain.mappers

import com.chus.clua.lorempicsum.domain.models.PictureDomainModel
import com.chus.clua.lorempicsum.presentation.models.PictureUiModel


class PictureUiModelMapper {
    fun mapFromDomain(from: PictureDomainModel) =
        PictureUiModel(
            id = from.id,
            author = from.author,
            downloadUrl = from.downloadUrl
        )

}