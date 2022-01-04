package com.surajrathod.surajtutz

import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {


    val mimeType: String = "text/html"
    val encoding: String = "UTF-8"
    lateinit var toolbar: Toolbar
    lateinit var imgPostTitle: ImageView

    // lateinit var progressBar: ProgressBar
    // lateinit var progressLayout: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val intent = getIntent()
        val title: String? = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        val imgPost = intent.getStringExtra("jetpack_featured_media_url")
        imgPostTitle = findViewById(R.id.imgPostTitle)

        supportActionBar?.title = title

        val contentView: WebView = findViewById(R.id.contentView)

        val pish =
            "<html>\n" +
                    "<head>\n" +
                    "<title>Page Title</title>\n" +
                    "<style>\n" +
                    "body {\n" +
                    "  background-color: black;\n" +
                    "  text-align: left;\n" +
                    "  color: white;\n" +
                    "  font-family: 'Oswald', sans-serif;\n" +
                    "}\n" +

                    "a { color : green;}" +
                    "</style>\n" +
                    "\n" +
                    "<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
                    "<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                    "<link href=\"https://fonts.googleapis.com/css2?family=Oswald:wght@300&display=swap\" rel=\"stylesheet\">\n" +
                    "</head>\n" +
                    "<body>"
        val pas = "</body></html>"
        val myHtmlString = pish + content + pas

        //progressBar = findViewById(R.id.spin_kit)
        //progressLayout = findViewById(R.id.progressLayout)


        if (content != null) {

            //progressLayout.visibility = View.GONE

            //Toast.makeText(this@DetailsActivity, "Web view error", Toast.LENGTH_SHORT).show()

            Picasso.get().load(imgPost).error(R.drawable.ic_launcher_background)
                .into(imgPostTitle);

            contentView.loadDataWithBaseURL("", myHtmlString, mimeType, encoding, "")

        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}