package dev.notoriouscoder4.newstimes.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import dev.notoriouscoder4.newstimes.data.database.entity.Source
import dev.notoriouscoder4.newstimes.data.model.Country
import dev.notoriouscoder4.newstimes.data.model.Language

@Composable
fun CountryListLayout(
    countryList: List<Country>,
    countryClicked: (Country) -> Unit
) {
    LazyColumn {
        items(countryList) {
            CountryItem(it) { country ->
                countryClicked(country)
            }
        }
    }
}

@Composable
fun LanguageListLayout(
    languageList: List<Language>,
    languageClicked: (Language) -> Unit
) {
    LazyColumn {
        items(languageList) {
            LanguageItem(it) { language ->
                languageClicked(language)
            }
        }
    }
}

@Composable
fun SourceListLayout(
    sourceList: List<Source>,
    sourceClicked: (Source) -> Unit
) {
    LazyColumn {
        items(sourceList) {
            SourceItem(it) { source ->
                sourceClicked(source)
            }
        }
    }
}