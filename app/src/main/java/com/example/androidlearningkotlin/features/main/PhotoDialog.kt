package com.example.androidlearningkotlin.features.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.androidlearningkotlin.R
import kotlinx.android.synthetic.main.fragment_dialog.*
import org.koin.android.viewmodel.ext.android.sharedViewModel


class PhotoDialog: DialogFragment() {

    private var id : Int?= null
    private var url : String?= null
    private var isFavorite: Boolean?= null
    private val mainViewModel by sharedViewModel<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(ARG_ID)
            url = it.getString(ARG_URL)
            isFavorite = it.getBoolean(ARG_FAV)
        }
    }

    companion object {
        const val TAG = "PhotoDialog"
        private const val ARG_ID = "argID"
        private const val ARG_URL = "argURL"
        private const val ARG_FAV = "argFAV"

        fun newInstance(id: Int, url: String, isFavorite: Boolean) = PhotoDialog().apply {
            arguments = Bundle().apply {
                putInt(ARG_ID, id)
                putString(ARG_URL, url)
                putBoolean(ARG_FAV, isFavorite)
            }
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dialog, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(view)
            .load(url)
            .centerCrop()
            .into(dialog_photo)


        //init buttons
        val addFavBtn: Button = dialog_btn_favorite
        val removeFavBtn: Button = dialog_btn_favorite_remove
        val cancelButton: Button = dialog_btn_close

        if(isFavorite!!){
            addFavBtn.visibility = View.GONE
            removeFavBtn.visibility = View.VISIBLE
        }

        addFavBtn.setOnClickListener(){
            mainViewModel.addFavPhoto(id!!)
            addFavBtn.visibility = View.GONE
            removeFavBtn.visibility = View.VISIBLE
        }
        removeFavBtn.setOnClickListener(){
            mainViewModel.removeFavPhoto(id!!)
            removeFavBtn.visibility = View.GONE
            addFavBtn.visibility = View.VISIBLE
        }
        cancelButton.setOnClickListener {
            dismiss()
        }
    }
}