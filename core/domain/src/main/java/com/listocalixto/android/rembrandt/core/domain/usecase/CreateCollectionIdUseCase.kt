package com.listocalixto.android.rembrandt.core.domain.usecase

import java.util.UUID
import javax.inject.Inject

class CreateCollectionIdUseCase @Inject constructor() {
    operator fun invoke(): String {
        return UUID.randomUUID().toString()
    }
}
