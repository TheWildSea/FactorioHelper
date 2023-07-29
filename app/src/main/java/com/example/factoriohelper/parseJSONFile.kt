package com.example.factoriohelper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

data class Item(
    val name: String,
    val group: String,
    val icon_col: Int,
    val icon_row: Int,
    val description: String?,
    val localized_name: Map<String, String>,
    val recipe: RecipeDetails?
) {
    fun getLocalizedName(languageCode: String): String? {
        return localized_name[languageCode]
    }
}

data class VanillaData(
    val items: Map<String, Item>,
    val recipes: Map<String, RecipeDetails>
)

fun loadVanillaData(context: Context): VanillaData? {
    val jsonFileName = "vanilla.json"
    val gson = Gson()

    try {
        val inputStream = context.assets.open(jsonFileName)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val jsonString = reader.use { it.readText() }

        val dataJson = JSONObject(jsonString)
        val itemsJson = dataJson.getJSONObject("items")
        val items = gson.fromJson<Map<String, Item>>(
            itemsJson.toString(),
            object : TypeToken<Map<String, Item>>() {}.type
        )

        val recipesJson = dataJson.getJSONObject("recipes")
        val recipes = gson.fromJson<Map<String, RecipeDetails>>(
            recipesJson.toString(),
            object : TypeToken<Map<String, RecipeDetails>>() {}.type
        )

        return VanillaData(items, recipes)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return null
}

fun loadIconsFromPNG(context: Context, pngFileName: String, iconSize: Int, rows: Int, columns: Int): List<Bitmap> {
    val icons = mutableListOf<Bitmap>()

    try {
        val inputStream = context.assets.open(pngFileName)
        val bitmap = BitmapFactory.decodeStream(inputStream)

        val iconWidth = bitmap.width / columns
        val iconHeight = bitmap.height / rows

        for (row in 0 until rows) {
            for (column in 0 until columns) {
                val left = column * iconWidth
                val top = row * iconHeight
                val right = left + iconWidth
                val bottom = top + iconHeight

                val iconRect = Rect(left, top, right, bottom)
                val iconBitmap = Bitmap.createBitmap(bitmap, iconRect.left, iconRect.top, iconWidth, iconHeight)
                val scaledIconBitmap = Bitmap.createScaledBitmap(iconBitmap, iconSize, iconSize, false)
                icons.add(scaledIconBitmap)
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return icons
}











