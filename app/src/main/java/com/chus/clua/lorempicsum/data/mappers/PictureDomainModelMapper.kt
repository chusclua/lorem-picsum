package com.chus.clua.lorempicsum.data.mappers

import com.chus.clua.lorempicsum.data.models.PictureRemoteModel
import com.chus.clua.lorempicsum.domain.models.PictureDomainModel


class PictureDomainModelMapper {
    fun mapFromRemote(from: PictureRemoteModel) =
        PictureDomainModel(
            id = from.id,
            author = from.author,
            height = from.height,
            width = from.width,
            url = from.url,
            downloadUrl = from.downloadUrl
        )

}