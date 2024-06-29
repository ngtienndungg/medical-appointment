package vn.dungnt.medpro.domain.entities

import androidx.annotation.DrawableRes
import vn.dungnt.medpro.R

data class LanguageEntity(
    val id: Int,
    val name: String,
    @DrawableRes val icon: Int
) {

    fun getLanguageCode() =
        LanguageType.entries.find { it.type == id }?.code ?: LanguageType.ENGLISH.code

    companion object {
        fun getAreaPhoneCodeModel(): List<LanguageEntity> {
            return listOf(
                LanguageEntity(1, "+84", R.drawable.flag_viet_nam),
                LanguageEntity(2, "+65", R.drawable.flag_singapore),
                LanguageEntity(3, "+91", R.drawable.flag_india),
                LanguageEntity(4, "+62", R.drawable.flag_indonesia),
                LanguageEntity(5, "+60", R.drawable.flag_malaysia),
            )
        }

        fun getLanguageList(): List<LanguageEntity> {
            return listOf(
                LanguageEntity(1, "VN", R.drawable.flag_viet_nam),
                LanguageEntity(2, "English", R.drawable.flag_american)
            )
        }
    }
}

enum class LanguageType(val type: Int, val code: String) {
    VN(1, "vi"), ENGLISH(2, "en")
}