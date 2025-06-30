package com.example.gymlog.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gymlog.R
import com.example.gymlog.databinding.ItemExerciseBinding
import com.example.gymlog.model.ExerciseModel
import com.example.gymlog.ui.adapters.diff.ExerciseDiffCallback

class ExerciseAdapter(
    private val onExerciseClick: (ExerciseModel) -> Unit,
    private val onDeleteClick: (ExerciseModel) -> Unit,
    private val onFavoriteClick: (ExerciseModel) -> Unit
) : ListAdapter<ExerciseModel, ExerciseAdapter.ExerciseViewHolder>(ExerciseDiffCallback()) {

    inner class ExerciseViewHolder(private val binding: ItemExerciseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(exercise: ExerciseModel) {
            binding.apply {
                tvName.text = exercise.name
                tvCategory.text = exercise.category
                tvSeriesReps.text = "${exercise.series}x${exercise.repetitions}"
                tvNotes.text = exercise.notes
                tvNotes.isVisible = !exercise.notes.isNullOrEmpty()

                buttonFavorite.setImageResource(
                    if (exercise.isFavorite) R.drawable.ic_favorite
                    else R.drawable.ic_favorite_border
                )

                root.setOnClickListener {
                    onExerciseClick(exercise)
                }

                buttonDelete.setOnClickListener {
                    onDeleteClick(exercise)
                }

                buttonFavorite.setOnClickListener {
                    onFavoriteClick(exercise)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        return ExerciseViewHolder(
            ItemExerciseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}