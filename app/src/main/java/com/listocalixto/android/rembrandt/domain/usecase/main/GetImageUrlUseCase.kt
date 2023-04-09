package com.listocalixto.android.rembrandt.domain.usecase.main

import com.listocalixto.android.rembrandt.domain.utility.QualityImageType
import javax.inject.Inject

class GetImageUrlUseCase @Inject constructor() {

    operator fun invoke(imageId: String, qualityType: QualityImageType): String {
        return "https://www.artic.edu/iiif/2/$imageId/full/${qualityType.value},/0/default.jpg"
    }
}
