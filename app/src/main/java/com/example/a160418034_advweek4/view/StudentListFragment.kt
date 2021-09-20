package com.example.a160418034_advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160418034_advweek4.R
import com.example.a160418034_advweek4.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_student_list.*


class StudentListFragment : Fragment() {
    private lateinit var ViewModel:ListViewModel
    private val studentListAdapter = StudentListAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        ViewModel.refresh()

        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = studentListAdapter

        refreshlayout.setOnRefreshListener {
            recView.visibility = View.GONE
            txtError.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
            ViewModel.refresh()
            refreshlayout.isRefreshing = false
        }

        observeViewModel()
    }

    fun observeViewModel(){
        ViewModel.studentsLD.observe(viewLifecycleOwner, Observer {
            studentListAdapter.updateStudentList(it)
        })

        ViewModel.studentLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it) {
                txtError.visibility = View.VISIBLE
            } else {
                txtError.visibility = View.GONE
            }
        })

        ViewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it) {
                progressLoad.visibility = View.VISIBLE
                recView.visibility = View.GONE
            } else {
                progressLoad.visibility = View.GONE
                recView.visibility = View.VISIBLE
            }
        })
    }
}