package me.bamtoll.obi.happyviewer.Gallery

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import me.bamtoll.obi.happyviewer.PieceActivity
import me.bamtoll.obi.happyviewer.R
import me.bamtoll.obi.happyviewer.TagButton

class GalleryAdapter(data: Array<GalleryItem>): RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    private var mData: Array<GalleryItem> = data

    companion object {
        private const val THUMBNAIL_URL = "https://aa.hiyobi.me/tn/"
        private const val IMAGE_FORMT = ".jpg"
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_gallery, p0, false))
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        Picasso.get().load(THUMBNAIL_URL + mData[p1].inherenceCode + IMAGE_FORMT).fit().placeholder(R.mipmap.ic_launcher).into(p0.thumbnailImage)
        p0.titleText.text = mData[p1].title

        if (mData[p1].infoItem.tag != null) {
            var tags: List<String> = mData[p1].infoItem.tag!!

            if (tags[0].trim() != "") {
                for (tag: String in tags) {
                    p0.tagLayout.addView(TagButton(p0.context, tag))
                }
            }
        }

        p0.itemView.setOnClickListener{ v ->
            Log.d("AsDf", mData[p1].inherenceCode + " " + p1)
            val intent = Intent(v.context.applicationContext, PieceActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("inherence_code", mData[p1].inherenceCode)
            v.context.applicationContext.startActivity(intent)
            p0.context.applicationContext
        }
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)

        holder.tagLayout.removeAllViews()
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val context: Context = view.context

        var thumbnailImage: ImageView = itemView.findViewById(R.id.image_gallery_thumbnail) as ImageView
        var titleText: TextView = itemView.findViewById(R.id.text_gallery_title) as TextView
        var tagLayout: GridLayout = itemView.findViewById(R.id.layout_tags) as GridLayout
    }
}