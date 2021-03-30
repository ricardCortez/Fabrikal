package com.example.fabrikal

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fabrikal.adapters.ShoesHomeAdapter
import com.example.fabrikal.model.ShoeHomeItem
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    lateinit var shoesHomeAdapter : ShoesHomeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shoesRecyclerView.layoutManager = GridLayoutManager(context,2)

        var shoeList : MutableList<ShoeHomeItem> = mutableListOf()
        shoeList.add(ShoeHomeItem("ABCD","ESTO ES UN ZAPATO","10.00","https://i02.appmifile.com/363_operator_in/13/10/2020/c7bde508f128d7b1fd10a224d18a5333.png"))
        shoeList.add(ShoeHomeItem("ABCD2","ESTO ES UN ZAPATO","20.00","https://i02.appmifile.com/363_operator_in/13/10/2020/c7bde508f128d7b1fd10a224d18a5333.png"))
        shoeList.add(ShoeHomeItem("ABCD3","ESTO ES UN ZAPATO","30.00","https://i02.appmifile.com/363_operator_in/13/10/2020/c7bde508f128d7b1fd10a224d18a5333.png"))
        shoeList.add(ShoeHomeItem("ABCD4","ESTO ES UN ZAPATO","50.00","https://i02.appmifile.com/363_operator_in/13/10/2020/c7bde508f128d7b1fd10a224d18a5333.png"))
        shoeList.add(ShoeHomeItem("ABCD5","ESTO ES UN ZAPATO","60.00","https://i02.appmifile.com/363_operator_in/13/10/2020/c7bde508f128d7b1fd10a224d18a5333.png"))
        shoeList.add(ShoeHomeItem("ABCD6","ESTO ES UN ZAPATO","70.00","https://i02.appmifile.com/363_operator_in/13/10/2020/c7bde508f128d7b1fd10a224d18a5333.png"))
        shoeList.add(ShoeHomeItem("ABCD","ESTO ES UN ZAPATO","10.00","https://i02.appmifile.com/363_operator_in/13/10/2020/c7bde508f128d7b1fd10a224d18a5333.png"))
        shoeList.add(ShoeHomeItem("ABCD2","ESTO ES UN ZAPATO","20.00","https://i02.appmifile.com/363_operator_in/13/10/2020/c7bde508f128d7b1fd10a224d18a5333.png"))
        shoeList.add(ShoeHomeItem("ABCD3","ESTO ES UN ZAPATO","30.00","https://i02.appmifile.com/363_operator_in/13/10/2020/c7bde508f128d7b1fd10a224d18a5333.png"))
        shoeList.add(ShoeHomeItem("ABCD4","ESTO ES UN ZAPATO","50.00","https://i02.appmifile.com/363_operator_in/13/10/2020/c7bde508f128d7b1fd10a224d18a5333.png"))
        shoeList.add(ShoeHomeItem("ABCD5","ESTO ES UN ZAPATO","60.00","https://i02.appmifile.com/363_operator_in/13/10/2020/c7bde508f128d7b1fd10a224d18a5333.png"))
        shoeList.add(ShoeHomeItem("ABCD6","ESTO ES UN ZAPATO","70.00","https://i02.appmifile.com/363_operator_in/13/10/2020/c7bde508f128d7b1fd10a224d18a5333.png"))

        shoesHomeAdapter = ShoesHomeAdapter(shoeList)
        shoesHomeAdapter.onItemClick = { shoeHomeItem ->
            var intent = Intent(activity,ProductActivity::class.java)
            startActivity(intent)
        }
        shoesRecyclerView.adapter = shoesHomeAdapter

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