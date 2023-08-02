package com.listocalixto.android.rembrandt.core.ui.navigation

import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import com.listocalixto.android.rembrandt.core.ui.R.drawable.ic_explore
import com.listocalixto.android.rembrandt.core.ui.R.drawable.ic_explore_outlined
import com.listocalixto.android.rembrandt.core.ui.R.drawable.ic_favorite
import com.listocalixto.android.rembrandt.core.ui.R.drawable.ic_favorite_outlined
import com.listocalixto.android.rembrandt.core.ui.R.drawable.ic_home
import com.listocalixto.android.rembrandt.core.ui.R.drawable.ic_home_outlined

enum class BottomNavTabType(
    @DrawableRes private val iconSelected: Int,
    @DrawableRes private val iconUnselected: Int,
) {
    Home(iconSelected = ic_home, iconUnselected = ic_home_outlined),
    Explore(iconSelected = ic_explore, iconUnselected = ic_explore_outlined),
    Favorites(iconSelected = ic_favorite, iconUnselected = ic_favorite_outlined),
    ;

    fun getIcon(resources: Resources, theme: Resources.Theme?, isSelected: Boolean): Drawable? {
        val icon = if (isSelected) iconSelected else iconUnselected
        return ResourcesCompat.getDrawable(resources, icon, theme)
    }
}
