package net.slickdeals.android.app

import android.app.ListActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import android.R
import android.widget.ListView
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.activity_main.*


class DealsListAdapter : ListActivity() {

    private val dealList: MutableList<DealSummary> = mutableListOf()
    private val adapter by lazy {  }

    private val  deals = listOf<DealSummary>(
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


        simpleListView = findViewById(R.id.simpleListView) as ListView

        val arrayList = ArrayList()
        for (i in 0 until animalName.length) {
            val hashMap = HashMap()//create a hashmap to store the data in key value pair
            hashMap.put("name", animalName[i])
            hashMap.put("image", animalImages[i] + "")
            arrayList.add(hashMap)//add the hashmap into arrayList
        }
        val from = arrayOf("name", "image")//string array
        val to = intArrayOf(R.id.textView, R.id.imageView)//int array of views id's
        val simpleAdapter = SimpleAdapter(
            this,
            arrayList,
            R.layout.list_view_items,
            from,
            to
        )//Create object and set the parameters for simpleAdapter
        simpleListView.setAdapter(simpleAdapter)//sets the adapter for listView


        listAdapter()

    }

    private fun makeAdapter(dealList: List<DealSummary>) {
        ArrayAdapter(this, R.layout.deal_card_layout, dealList)
    }
}


data class DealSummary(
    val price: Double,
    val title: String,
    val extra: String,
    val voteCount: Int,
    val commentCount: Int
)



class DealCellViewHolder(view: View) {
    private lateinit var priceLabel: TextView
    private lateinit var nameLabel: TextView
    private lateinit var extraLabel: TextView
    private lateinit var votesLabel: TextView
    private lateinit var commentsLabel: TextView

    fun bindDealSummary(deal: DealSummary) {
        priceLabel.text = deal.price.toString()
        nameLabel.text = deal.title
        extraLabel.text = deal.extra
        votesLabel.text = deal.voteCount.toString()
        commentsLabel.text = deal.commentCount.toString()
    }
}




