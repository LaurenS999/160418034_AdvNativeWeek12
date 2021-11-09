package com.example.a160418034_advweek4.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.a160418034_advweek4.R
import com.example.a160418034_advweek4.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_student_detail.*
import androidx.lifecycle.Observer
import com.example.a160418034_advweek4.databinding.FragmentStudentDetailBinding
import com.example.a160418034_advweek4.util.loadImage
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.view.*
import java.util.concurrent.TimeUnit


class StudentDetailFragment : Fragment(), NotifButtonClicklistener {
    private lateinit var ViewModel: DetailViewModel
    private lateinit var dataBinding:FragmentStudentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater, R.layout.fragment_student_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var studentId = StudentDetailFragmentArgs.fromBundle(requireArguments()).StudentId

        ViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        ViewModel.fetch(studentId)

        dataBinding.notifListener =this

//        txtid.setText(ViewModel.studentLD.value?.id)
//        txtname.setText(ViewModel.studentLD.value?.name)
//        txtBod.setText(ViewModel.studentLD.value?.bod)
//        txtPhone.setText(ViewModel.studentLD.value?.phone)
//
//        view.imageView2.loadImage(ViewModel.studentLD.value?.photoUrl, view.progressBar2)

        observeViewModel()
    }

    fun observeViewModel(){
        ViewModel.studentLD.observe(viewLifecycleOwner, Observer {
            dataBinding.student = it
//            txtid.setText(it.id)
//            txtname.setText(it.name)
//            txtBod.setText(it.bod)
//            txtPhone.setText(it.phone)
//
//            imageView2.loadImage(ViewModel.studentLD.value?.photoUrl, progressBar2)
//
//            var student = it
//            btnNotif.setOnClickListener {
//                Observable.timer(5, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        Log.d("Messages", "five seconds")
//                        MainActivity.showNotification(student.name.toString(),
//                            "A new notification created",
//                            R.drawable.ic_baseline_person_24)
//                    }
//            }
        })

    }

    override fun onButtonNotifClick(v: View) {
        Observable.timer(5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("Messages", "five seconds")
                MainActivity.showNotification(v.tag.toString(),
                    "A new notification created",
                    R.drawable.ic_baseline_person_24)
            }
    }
}