package com.listocalixto.android.rembrandt.domain.usecase.main

import com.listocalixto.android.rembrandt.core.domain.GetArtworksAccordingSearchUseCase.Companion.applyCorrectFormatInTheQuery
import com.listocalixto.android.rembrandt.core.domain.GetArtworksAccordingSearchUseCase.Companion.validateQuery
import junitparams.FileParameters
import junitparams.JUnitParamsRunner
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
class GetArtworksAccordingSearchUseCaseTest {

    @Test
    @FileParameters("$BASE_ASSET_URI/get_artworks_according_search_test_01_parameters.csv")
    fun validateQuery_correct_returnTrue(
        query: String,
        expected: Boolean
    ) {
        Assert.assertTrue(validateQuery(query))
    }

    @Test
    @FileParameters("$BASE_ASSET_URI/get_artworks_according_search_test_02_parameters.csv")
    fun validateQuery_incorrect_returnError(
        query: String,
        expected: Boolean
    ) {
        Assert.assertThrows(Exception::class.java) {
            validateQuery(query)
        }
    }

    @Test
    @FileParameters("$BASE_ASSET_URI/get_artworks_according_search_test_03_parameters.csv")
    fun validateQueryIsFormatted_success_returnQueryFormatted(
        query: String,
        expected: String
    ) {
        Assert.assertEquals(expected, applyCorrectFormatInTheQuery(query))
    }

    companion object {
        private const val BASE_ASSET_URI =
            "src/test/java/com/listocalixto/android/rembrandt/assets/domain/usecase/main/getartworksaccordingsearch"
    }
}
