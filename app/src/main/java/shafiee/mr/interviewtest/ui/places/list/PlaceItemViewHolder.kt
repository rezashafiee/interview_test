package shafiee.mr.interviewtest.ui.places.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import shafiee.mr.interviewtest.databinding.ItemPlaceBinding
import shafiee.mr.interviewtest.model.Item

class PlaceItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: Item?, itemBinding: ItemPlaceBinding) {
        itemBinding.textViewPlaceName.text = item?.venue?.name
    }
}