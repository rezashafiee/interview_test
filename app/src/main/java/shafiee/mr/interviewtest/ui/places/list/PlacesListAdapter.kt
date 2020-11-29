package shafiee.mr.interviewtest.ui.places.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import shafiee.mr.interviewtest.databinding.ItemPlaceBinding
import shafiee.mr.interviewtest.model.Item

class PlacesListAdapter : PagedListAdapter<Item, PlaceItemViewHolder>(diffCallback) {

    private lateinit var itemBinding: ItemPlaceBinding

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Item>() {

            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem.venue?.id == newItem.venue?.id

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        itemBinding = ItemPlaceBinding.inflate(layoutInflater, parent, false)
        return PlaceItemViewHolder(itemBinding.root)
    }

    override fun onBindViewHolder(holder: PlaceItemViewHolder, position: Int) {
        holder.bind(getItem(position), itemBinding)
    }
}