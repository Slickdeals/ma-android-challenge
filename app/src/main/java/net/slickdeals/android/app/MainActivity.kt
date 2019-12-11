package net.slickdeals.android.app

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val  deals = arrayListOf<DealSummary>(
        DealSummary(
            45.0,
            "2 for 1 alligator paws at Super Doughnut Shop",
            "free shipping!",
            54,
            2),
        DealSummary(
            32.0,
            "FREE couch at local dealerships",
            "pickup only",
            15,
            11)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        populateListWithMockDeals()
    }

    private fun populateListWithMockDeals() {
        val listView = findViewById(R.id.simpleListView) as ListView
        listView.adapter = DealListAdapter(this, deals)
    }
}


data class DealSummary(
    val price: Double,
    val title: String,
    val extra: String,
    val voteCount: Int,
    val commentCount: Int
)


class DealListAdapter(
    private val context: Context,
    private val dataSource: ArrayList<DealSummary>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = inflater.inflate(R.layout.deal_card_layout, parent, false)
        val deal = getItem(position) as DealSummary
        val holder = DealCellViewHolder()
        holder.titleLabel = view.findViewById(R.id.deal_title) as TextView
        holder.priceLabel = view.findViewById(R.id.deal_price) as TextView
        holder.extraLabel = view.findViewById(R.id.deal_extra) as TextView
        holder.votesLabel = view.findViewById(R.id.vote_count) as TextView
        holder.commentsLabel = view.findViewById(R.id.comment_count) as TextView
        holder.bindDealSummary(deal)
        return view
    }
}


class DealCellViewHolder {
    lateinit var titleLabel: TextView
    lateinit var priceLabel: TextView
    lateinit var extraLabel: TextView
    lateinit var votesLabel: TextView
    lateinit var commentsLabel: TextView

    fun bindDealSummary(deal: DealSummary) {
        titleLabel.text = deal.title
        priceLabel.text = deal.price.toString()
        extraLabel.text = deal.extra
        votesLabel.text = deal.voteCount.toString()
        commentsLabel.text = deal.commentCount.toString()
    }
}

