package shafiee.mr.interviewtest.ui.places.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import shafiee.mr.interviewtest.databinding.ItemPlaceBinding
import shafiee.mr.interviewtest.model.Item

class PlacesListAdapter : RecyclerView.Adapter<PlaceItemViewHolder>() {

    private lateinit var itemBinding: ItemPlaceBinding
    var placeList = mutableListOf<Item?>()

    init {
        placeList = mutableListOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        itemBinding = ItemPlaceBinding.inflate(layoutInflater, parent, false)
        return PlaceItemViewHolder(itemBinding.root)
    }

    override fun onBindViewHolder(holder: PlaceItemViewHolder, position: Int) {
        holder.bind(placeList[position], itemBinding)
    }

    override fun getItemCount(): Int {
        return placeList.size
    }

    fun updateList(list: List<Item>?) {
        list?.forEach { item ->
            placeList.add(item)
        }
        notifyDataSetChanged()
    }

    fun setList(list: List<Item>?) {
        placeList = list?.toMutableList()!!
        notifyDataSetChanged()
    }
}