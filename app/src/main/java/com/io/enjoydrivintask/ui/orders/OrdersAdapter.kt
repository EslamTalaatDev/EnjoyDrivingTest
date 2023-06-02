package com.io.enjoydrivintask.ui.orders

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.io.enjoydrivintask.databinding.OrdersRowBinding
import com.io.enjoydrivintask.model.OrderMangmentModel
import com.io.enjoydrivintask.utils.SendSingleItemListener
import java.util.*


class OrdersAdapter(
    private val sendData: SendSingleItemListener<OrderMangmentModel>,

    ) : ListAdapter<OrderMangmentModel, OrdersAdapter.OffersViewHolder>(

    object : DiffUtil.ItemCallback<OrderMangmentModel>() {
        override fun areItemsTheSame(
            oldItem: OrderMangmentModel,
            newItem: OrderMangmentModel
        ): Boolean =
            oldItem.orderRef == newItem.orderRef

        override fun areContentsTheSame(
            oldItem: OrderMangmentModel,
            newItem: OrderMangmentModel
        ): Boolean =
            oldItem.equals(newItem)
    }
) {
    inner class OffersViewHolder(var categoriesBinding: OrdersRowBinding) :
        RecyclerView.ViewHolder(categoriesBinding.root) {
        @SuppressLint("ClickableViewAccessibility")
        internal fun bindItem(item: OrderMangmentModel) {
            categoriesBinding.apply {
                data = item
                clickListener = sendData
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersViewHolder =
        OffersViewHolder(
            OrdersRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: OffersViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }


}