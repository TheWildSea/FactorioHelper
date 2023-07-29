package com.example.factoriohelper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ElementAdapter : RecyclerView.Adapter<ElementAdapter.ElementViewHolder>() {
    private var elements: List<Element> = emptyList()

    fun setElements(elements: List<Element>) {
        this.elements = elements
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_element, parent, false)
        return ElementViewHolder(view)
    }

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        val element = elements[position]
        holder.bind(element)
    }

    override fun getItemCount(): Int = elements.size

    inner class ElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iconImageView: ImageView = itemView.findViewById(R.id.iconImageView)
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        private val recipeTextView: TextView = itemView.findViewById(R.id.recipeTextView)

        fun bind(element: Element) {
            println("Binding element: ${element.name}")
            iconImageView.setImageBitmap(element.iconBitmap)
            nameTextView.text = element.name
            descriptionTextView.text = element.description
            recipeTextView.text = element.recipe?.let { formatRecipe(it) }
        }

        private fun formatRecipe(recipe: RecipeDetails): String {
            val ingredients = recipe.ingredients.joinToString(", ") { "${it.amount} ${it.name}" }
            val results = recipe.results.joinToString(", ") { "${it.amount} ${it.name}" }
            return "Ingredients: $ingredients\nResults: $results"
        }
    }
}

