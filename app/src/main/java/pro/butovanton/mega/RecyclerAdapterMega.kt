package pro.butovanton.mega

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import pro.butovanton.mega.RecyclerAdapterMega.ViewHolderMega
import java.util.*

internal class RecyclerAdapterMega(private val itemClickListener: ItemClickListener, context: Context?) : RecyclerView.Adapter<ViewHolderMega>() {
    private var listModel: List<MModel>?
    private val mInflater: LayoutInflater
    private val context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMega {
        val view = mInflater.inflate(R.layout.item, parent, false)
        return ViewHolderMega(view)
    }

    override fun onBindViewHolder(holder: ViewHolderMega, position: Int) {
        holder.idTextView.text = listModel!![position].name
        Picasso
                .get()
                .load(listModel!![position].img)
                .into(holder.imageView)
        holder.itemView.setOnClickListener { itemClickListener.onItemClick(listModel!![position].id) }
    }

    fun set(models: List<MModel>?) {
        listModel = models
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (listModel == null) 0 else listModel!!.size
    }

    inner class ViewHolderMega(view: View) : RecyclerView.ViewHolder(view) {
        val idTextView: TextView
        val imageView: ImageView

        init {
            idTextView = view.findViewById<View>(R.id.name) as TextView
            imageView = view.findViewById(R.id.imageView)
        }
    }

    init {
        mInflater = LayoutInflater.from(context)
        listModel = ArrayList()
        this.context = context!!
    }
}