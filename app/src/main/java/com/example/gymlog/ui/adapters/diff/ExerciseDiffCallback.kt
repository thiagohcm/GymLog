package com.example.gymlog.ui.adapters.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.gymlog.model.ExerciseModel

class ExerciseDiffCallback : DiffUtil.ItemCallback<ExerciseModel>() {
    override fun areItemsTheSame(oldItem: ExerciseModel, newItem: ExerciseModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ExerciseModel, newItem: ExerciseModel): Boolean {
        return oldItem == newItem
    }
}