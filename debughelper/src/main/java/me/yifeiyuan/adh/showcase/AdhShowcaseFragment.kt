package me.yifeiyuan.adh.showcase

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.yifeiyuan.adh.R

/**
 * A fragment representing a list of Items.
 */
internal class AdhShowcaseFragment : Fragment() {

    private var provider: AdhShowcaseProvider? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is AdhShowcaseProvider) {
            provider = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.adh_fragment_showcase_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                adapter = AdhShowcaseAdapter(getShowcaseItemList())
                layoutManager = LinearLayoutManager(view.context)
                (adapter as RecyclerView.Adapter).notifyDataSetChanged()
            }
        }
        return view
    }

    protected fun getShowcaseItemList(): List<AdhShowcaseItem> {
        return if (provider == null) listOf() else provider!!.provideShowcaseItems()
    }

}