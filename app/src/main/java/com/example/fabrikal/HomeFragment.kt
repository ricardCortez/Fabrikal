package com.example.fabrikal

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fabrikal.adapters.ShoesHomeAdapter
import com.example.fabrikal.model.ShoeHomeItem
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    lateinit var shoesHomeAdapter : ShoesHomeAdapter
    private lateinit var dbReference: DatabaseReference
    val shoeList : ArrayList<ShoeHomeItem> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbReference = FirebaseDatabase.getInstance().getReference("Producto")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shoesRecyclerView.layoutManager = GridLayoutManager(context,2)




        shoesHomeAdapter = ShoesHomeAdapter(shoeList)
        shoesHomeAdapter.onItemClick = { shoeHomeItem ->
            val intent = Intent(activity,ProductActivity::class.java)
            intent.putExtra("PRODUCTO",shoeHomeItem)
            startActivity(intent)

        }
        shoesRecyclerView.adapter = shoesHomeAdapter


        dbReference.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                //TODO: Mostrar Error
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    snapshot.children.forEach { childSnapshot ->
                        childSnapshot.getValue(ShoeHomeItem::class.java)?.apply {
                            shoeList.add(this)
                        }

                    }

                    shoesHomeAdapter.notifyDataSetChanged()
                }
            }

        })

        searchButton.setOnClickListener {
            val intent = Intent(activity, SearchActivity::class.java)
            startActivity(intent)
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(/*param1: String, param2: String*/) =
                HomeFragment().apply {
                    /*arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }*/
                }
    }
}