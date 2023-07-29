package com.example.factoriohelper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class Element(
    val name: String,
    val description: String,
    val iconBitmap: Bitmap?,
    val iconCol: Int,
    val iconRow: Int,
    val recipe: RecipeDetails?
)

data class RecipeDetails(
    val name: String,
    val ingredients: List<Ingredients>,
    val results: List<Results>
)

data class Ingredients(
    val amount: Int,
    val name: String
)

data class Results(
    val amount: Int,
    val name: String
)

class ElementListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ElementAdapter
    private lateinit var context: Context
    private lateinit var pngFileName: String
    private var iconSize = 0
    private var rows = 0
    private var columns = 0
    private lateinit var defaultIconBitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_element_list)

        context = applicationContext
        pngFileName = "spritesheet.png"
        iconSize = 32
        rows = 16
        columns = 15

        defaultIconBitmap = BitmapFactory.decodeResource(resources, R.drawable.default_icon)

        recyclerView = findViewById(R.id.recyclerView)
        adapter = ElementAdapter()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val category = intent.getStringExtra("category")
        val vanillaData = loadVanillaData(context)
        val elements = vanillaData?.let { fetchElementsForCategory(category, it) }

        if (elements != null) {
            adapter.setElements(elements)
        } else {
            println("No elements found for category: $category")
        }
    }

    private fun fetchElementsForCategory(category: String?, vanillaData: VanillaData): List<Element> {
        val items = vanillaData.items.filter { it.value.group == category }
        val iconMapping = generateIconMapping(vanillaData)
        val recipes = vanillaData.recipes

        if (items.isEmpty()) {
            Log.i("ElementListActivity", "No elements found for category: $category")
        } else {
            Log.i("ElementListActivity", "Found ${items.size} elements for category: $category")
        }

        return items.mapNotNull { item ->
            val itemName = item.key
            val itemValue = item.value
            val iconBitmap = iconMapping[Pair(itemValue.icon_col, itemValue.icon_row)]
            val localizedName = itemValue.getLocalizedName("en")
            val recipe = recipes[itemName]

            if (recipe != null) {
                val ingredients = recipe.ingredients.map { ingredient ->
                    Ingredients(ingredient.amount, ingredient.name)
                }

                val results = recipe.results.map { result ->
                    Results(result.amount, result.name)
                }

                Element(
                    name = localizedName ?: itemName,
                    description = itemValue.description ?: "",
                    iconBitmap = iconBitmap,
                    iconCol = itemValue.icon_col,
                    iconRow = itemValue.icon_row,
                    recipe = RecipeDetails(
                        name = itemName,
                        ingredients = ingredients,
                        results = results
                    )
                )
            } else {
                null
            }
        }
    }

    private fun generateIconMapping(vanillaData: VanillaData): Map<Pair<Int, Int>, Bitmap> {
        val iconMapping = mutableMapOf<Pair<Int, Int>, Bitmap>()

        try {
            val inputStream = assets.open(pngFileName)
            val bitmap = BitmapFactory.decodeStream(inputStream)

            val iconWidth = bitmap.width / columns
            val iconHeight = bitmap.height / rows

            for ((_, item) in vanillaData.items) {
                val iconCol = item.icon_col
                val iconRow = item.icon_row

                if (iconCol in 0 until columns && iconRow in 0 until rows) {
                    val iconRect = Rect(
                        iconCol * iconWidth,
                        iconRow * iconHeight,
                        (iconCol + 1) * iconWidth,
                        (iconRow + 1) * iconHeight
                    )

                    if (iconRect.intersect(0, 0, bitmap.width, bitmap.height)) {
                        val iconBitmap = Bitmap.createBitmap(bitmap, iconRect.left, iconRect.top, iconRect.width(), iconRect.height())
                        val scaledIconBitmap = Bitmap.createScaledBitmap(iconBitmap, iconSize, iconSize, false)
                        iconMapping[Pair(iconCol, iconRow)] = scaledIconBitmap
                    } else {
                        iconMapping[Pair(iconCol, iconRow)] = defaultIconBitmap
                    }
                } else {
                    iconMapping[Pair(iconCol, iconRow)] = defaultIconBitmap
                }
            }

            bitmap.recycle()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return iconMapping
    }
}
