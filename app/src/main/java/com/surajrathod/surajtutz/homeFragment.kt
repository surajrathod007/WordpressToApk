package com.surajrathod.surajtutz

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.DoubleBounce
import com.github.ybq.android.spinkit.style.ThreeBounce
import org.json.JSONException
import org.json.JSONObject

//comment added on experimental (just Change)
//third commit
@Suppress("UNREACHABLE_CODE")
class homeFragment : Fragment() {

    lateinit var recyclerHome: RecyclerView //creating variable of recycler view

    lateinit var layoutManager: RecyclerView.LayoutManager // creating variable for layout manager


    //creating data for list
    //just simple commit
    val postInfoList = arrayListOf<Post>()


    lateinit var recyclerAdapter: HomeRecyclerAdapter //declaring adapter

    lateinit var progressBar : ProgressBar
    lateinit var progressLayout : RelativeLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        //return the view it can be also --> "return inflater.inflate(R.layout.fragment_home, container, false)"



        //progressbar

        progressBar = view.findViewById(R.id.spin_kit)
        progressLayout = view.findViewById(R.id.progressLayout)

        val ThreeBounce: Sprite
        ThreeBounce = ThreeBounce()

        progressBar.setIndeterminateDrawable(ThreeBounce);
        progressLayout.visibility = View.VISIBLE
        //initialise or connect  the recycler view

        recyclerHome =
            view.findViewById(R.id.recyclerHome) // we are using view. becuase , in fragments we have to do this , otherwise it will be error

        layoutManager =
            LinearLayoutManager(activity) //we are passing activity because its fragment , but in activiyu.kt we will pass "this" keyword in parameters


        //initialize adapter
        recyclerAdapter = HomeRecyclerAdapter(
            activity as Context,
            postInfoList
        ) //the "as" keyword is used for typecastig

        //if we type only activity then it will throw an error, so we typcasted it to Context. because its fragment activity


        //now atach adapter & layout manager

        recyclerHome.adapter = recyclerAdapter

        recyclerHome.layoutManager = layoutManager


        //making request
        val queue = Volley.newRequestQueue(activity as Context)
        val url =
            "https://surajtutz.000webhostapp.com/wp-json/wp/v2/posts?per_page=5"
        //making a request to the api

        val request =
            JsonArrayRequest(Request.Method.GET, url, null, { response ->


                try {

                    //hide progressbar

                        progressLayout.visibility = View.GONE

                    for (i in 0 until response.length()) {

                        val postObject: JSONObject = response.getJSONObject(i)

                        val titleObject : JSONObject = postObject.getJSONObject("title") //for title
                        val contentObject : JSONObject = postObject.getJSONObject("content") // for content
                        val postObjects = Post(


                            titleObject.getString("rendered"),
                            postObject.getString("jetpack_featured_media_url"),
                            contentObject.getString("rendered")


                        )

                        postInfoList.add(postObjects)

                        //initialize adapter
                        recyclerAdapter = HomeRecyclerAdapter(
                            activity as Context,
                            postInfoList
                        ) //the "as" keyword is used for typecastig

                        //if we type only activity then it will throw an error, so we typcasted it to Context. because its fragment activity

                        recyclerHome.adapter = recyclerAdapter

                        recyclerHome.layoutManager = layoutManager

                    }
                } catch (e: JSONException) {
                    Toast.makeText(
                        activity as Context,
                        "Some unexpected error occure",
                        Toast.LENGTH_SHORT
                    ).show()
                }


            }, Response.ErrorListener {
                Toast.makeText(activity as Context, "Volley error ocuured", Toast.LENGTH_SHORT)
                    .show()
            })

        queue.add(request)

        return view
    }


}