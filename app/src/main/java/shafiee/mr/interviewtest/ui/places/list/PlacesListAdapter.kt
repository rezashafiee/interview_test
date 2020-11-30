package shafiee.mr.interviewtest.ui.places.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import shafiee.mr.interviewtest.R
import shafiee.mr.interviewtest.model.Item

class PlacesListAdapter(private val requestManager: RequestManager) :
    RecyclerView.Adapter<PlaceItemViewHolder>() {

    var placeList = mutableListOf<Item?>()

    init {
        placeList = mutableListOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_place, parent, false)
        return PlaceItemViewHolder(itemView, requestManager)
    }

    override fun onBindViewHolder(holder: PlaceItemViewHolder, position: Int) {
        holder.bind(placeList[position])
    }

    override fun getItemCount(): Int {
        return placeList.size
    }

    fun updateList(list: List<Item>?) {
        list?.forEach { item ->
            placeList.add(item)
        }
    }

    fun setList(list: List<Item>?) {
        placeList = list?.toMutableList()!!
        notifyDataSetChanged()
    }
}