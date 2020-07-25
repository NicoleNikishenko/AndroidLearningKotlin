package com.example.androidlearningkotlin.features.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlearningkotlin.R
import com.example.androidlearningkotlin.features.main.MainAdapter.PhotoAdapterListener
import com.example.androidlearningkotlin.features.main.binders.GridList
import com.example.androidlearningkotlin.features.main.binders.HorizontalList

import com.example.androidlearningkotlin.models.Photo
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.viewmodel.ext.android.sharedViewModel


class MainFragment: Fragment() , PhotoAdapterListener {

    private val mainViewModel by sharedViewModel<MainViewModel>()

    private val adapter = MainAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initObservers()
        initViews()

        //todo: 3. fetch data from repo  //viewModel.getImageList
        mainViewModel.getPhotosList()
    }

    private fun initUi(){

        //todo - 1. init Ui
        val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL,false)
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = adapter
    }
    private fun initObservers(){
        mainViewModel.bigPhotosEvent.observe(viewLifecycleOwner ,Observer<List<Photo>>{
            val horizontalList = HorizontalList(it)
            adapter.updateListData(horizontalList)
        })
        mainViewModel.smallPhotosEvent.observe(viewLifecycleOwner ,Observer<List<Photo>>{
            val gridList = GridList(it)
            adapter.updateListData(gridList)
        })
        mainViewModel.favePhotosEvent.observe(viewLifecycleOwner,Observer<List<Photo>>{
            val horizontalList = HorizontalList(it)
            adapter.updateFavListData(horizontalList)
        })
    }
    private fun initViews(){

    }

    override fun onPhotoClick(photo: Photo) {
        super.onPhotoClick(photo)
        PhotoDialog.newInstance(photo.ID, photo.url, photo.favorite).show(this.activity!!.supportFragmentManager, PhotoDialog.TAG);
    }
}

