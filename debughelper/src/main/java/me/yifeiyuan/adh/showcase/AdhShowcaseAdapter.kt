package me.yifeiyuan.adh.showcase

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import me.yifeiyuan.adh.R

class AdhShowcaseAdapter(
    private val values: List<AdhShowcaseItem>
) : RecyclerView.Adapter<AdhShowcaseAdapter.ViewHolder>() {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adh_fragment_showcase, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.contentView.text = item.content
        if (item.clickListener != null) {
            holder.itemView.setOnClickListener(item.clickListener)
        } else if (item.targetIntent != null) {
            holder.itemView.setOnClickListener {
                it.context.startActivity(item.targetIntent)
            }
        }
    }

    override fun getItemCount(): Int {
        return values.size
    }

    override fun getItemViewType(position: Int): Int {
        return 1
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}