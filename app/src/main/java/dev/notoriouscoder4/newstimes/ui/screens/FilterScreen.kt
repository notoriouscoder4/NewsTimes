package dev.notoriouscoder4.newstimes.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.notoriouscoder4.newstimes.R
import dev.notoriouscoder4.newstimes.common.NoInternetException
import dev.notoriouscoder4.newstimes.data.database.entity.Source
import dev.notoriouscoder4.newstimes.data.model.Country
import dev.notoriouscoder4.newstimes.data.model.Language
import dev.notoriouscoder4.newstimes.ui.base.ShowError
import dev.notoriouscoder4.newstimes.ui.base.ShowLoading
import dev.notoriouscoder4.newstimes.ui.base.UIState
import dev.notoriouscoder4.newstimes.ui.components.CountryListLayout
import dev.notoriouscoder4.newstimes.ui.components.LanguageListLayout
import dev.notoriouscoder4.newstimes.ui.components.SourceListLayout
import dev.notoriouscoder4.newstimes.ui.viewmodels.filters.CountryFilterViewModel
import dev.notoriouscoder4.newstimes.ui.viewmodels.filters.LanguageFilterViewModel
import dev.notoriouscoder4.newstimes.ui.viewmodels.filters.SourceFilterViewModel

@Composable
fun CountryScreen(
    countryFilterViewModel: CountryFilterViewModel = hiltViewModel(),
    countryClicked: (Country) -> Unit
) {
    val countryUiState: UIState<List<Country>> by countryFilterViewModel.countryItem.collectAsStateWithLifecycle()

    when (countryUiState) {
        is UIState.Loading -> {
            ShowLoading()
        }

        is UIState.Failure -> {
            var errorText = stringResource(id = R.string.something_went_wrong)
            if ((countryUiState as UIState.Failure<List<Country>>).throwable is NoInternetException) {
                errorText = stringResource(id = R.string.no_internet_available)
            }
            ShowError(text = errorText, retryEnabled = true) {
                countryFilterViewModel.getCountries()
            }
        }

        is UIState.Success -> {
            CountryListLayout(countryList = (countryUiState as UIState.Success<List<Country>>).data) {
                countryClicked(it)
            }
        }

        is UIState.Empty -> {

        }
    }
}

@Composable
fun LanguageScreen(
    languageFilterViewModel: LanguageFilterViewModel = hiltViewModel(),
    languageClicked: (Language) -> Unit
) {
    val languageUiState: UIState<List<Language>> by languageFilterViewModel.languageItem.collectAsStateWithLifecycle()

    when (languageUiState) {
        is UIState.Loading -> {
            ShowLoading()
        }

        is UIState.Failure -> {
            var errorText = stringResource(id = R.string.something_went_wrong)
            if ((languageUiState as UIState.Failure<List<Language>>).throwable is NoInternetException) {
                errorText = stringResource(id = R.string.no_internet_available)
            }
            ShowError(
                text = errorText,
                retryEnabled = true
            ) {
                languageFilterViewModel.getLanguage()
            }
        }

        is UIState.Success -> {
            LanguageListLayout(languageList = (languageUiState as UIState.Success<List<Language>>).data) {
                languageClicked(it)
            }
        }

        is UIState.Empty -> {

        }
    }
}

@Composable
fun SourceScreen(
    sourceFilterViewModel: SourceFilterViewModel = hiltViewModel(),
    sourceClicked: (Source) -> Unit
) {
    val sourceUiState: UIState<List<Source>> by sourceFilterViewModel.sourceItem.collectAsStateWithLifecycle()

    when (sourceUiState) {
        is UIState.Loading -> {
            ShowLoading()
        }

        is UIState.Failure -> {
            var errorText = stringResource(id = R.string.something_went_wrong)
            if ((sourceUiState as UIState.Failure<List<Source>>).throwable is NoInternetException) {
                errorText = stringResource(id = R.string.no_internet_available)
            }
            ShowError(
                text = errorText,
                retryEnabled = true
            ) {
                sourceFilterViewModel.getSources()
            }
        }

        is UIState.Success -> {
            SourceListLayout(sourceList = (sourceUiState as UIState.Success<List<Source>>).data) {
                sourceClicked(it)
            }
        }

        is UIState.Empty -> {

        }
    }
}