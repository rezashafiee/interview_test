package shafiee.mr.interviewtest.ui.places.list

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import shafiee.mr.interviewtest.R
import shafiee.mr.interviewtest.model.Item

class PlaceItemViewHolder(itemView: View, private val requestManager: RequestManager) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(item: Item?) {
        itemView.findViewById<TextView>(R.id.textView_placeName).text = item?.venue?.name
        /* val imageView = itemView.findViewById<ImageView>(R.id.imageView_placeType)
         requestManager.load(item?.venue?.categories?.get(0)?.icon?.prefix).into(imageView)*/
    }
}