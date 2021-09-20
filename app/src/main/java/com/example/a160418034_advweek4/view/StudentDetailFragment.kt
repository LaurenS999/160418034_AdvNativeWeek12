package com.example.a160418034_advweek4.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.a160418034_advweek4.R
import com.example.a160418034_advweek4.viewmodel.DetailViewModel
import com.example.a160418034_advweek4.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.fragment_student_list.*
import androidx.lifecycle.Observer
import com.example.a160418034_advweek4.util.loadImage
import kotlinx.android.synthetic.main.fragment_student_detail.view.*
import kotlinx.android.synthetic.main.student_list_item.view.*


class StudentDetailFragment : Fragment() {
    private lateinit var ViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var studentId = ""
        if(arguments != null) {
            studentId = StudentDetailFragmentArgs.fromBundle(requireArguments()).StudentId
        }

        ViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        ViewModel.fetch(studentId)

        txtid.setText(ViewModel.studentLD.value?.id)
        txtname.setText(ViewModel.studentLD.value?.name)
        txtBod.setText(ViewModel.studentLD.value?.bod)
        txtPhone.setText(ViewModel.studentLD.value?.phone)

        view.imageView2.loadImage(ViewModel.studentLD.value?.photoUrl, view.progressBar2)

        observeViewModel()
    }

    fun observeViewModel(){
        ViewModel.studentLD.observe(viewLifecycleOwner, Observer {
            txtid.setText(it.id)
            txtname.setText(it.name)
            txtBod.setText(it.bod)
            txtPhone.setText(it.phone)

            imageView2.loadImage(ViewModel.studentLD.value?.photoUrl, progressBar2)
        })

    }
}