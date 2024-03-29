package com.googry.googrybaserecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

abstract class BaseRecyclerView {

    abstract class Adapter<ITEM : Any, B : ViewDataBinding>(
        @LayoutRes private val layoutResId: Int,
        private val bindingVariableId: Int? = null
    ) : RecyclerView.Adapter<ViewHolder<B>>() {

        private val items = mutableListOf<ITEM>()

        fun replaceAll(items: List<ITEM>?) {
            items?.let {
                this.items.run {
                    clear()
                    addAll(it)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            object : ViewHolder<B>(
                layoutResId = layoutResId,
                parent = parent,
                bindingVariableId = bindingVariableId
            ) {}

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: ViewHolder<B>, position: Int) {
            holder.onBindViewHolder(items[position])
        }
    }

    abstract class ViewHolder<B : ViewDataBinding>(
        @LayoutRes layoutResId: Int,
        parent: ViewGroup,
        private val bindingVariableId: Int?
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
    ) {

        protected val binding: B = DataBindingUtil.bind(itemView)!!

        fun onBindViewHolder(item: Any?) {
            try {
                bindingVariableId?.let {
                    binding.setVariable(it, item)

                    binding.root.setOnClickListener{

                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}