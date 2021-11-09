package com.example.a160418034_advweek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160418034_advweek4.R
import com.example.a160418034_advweek4.databinding.StudentListItemBinding
import com.example.a160418034_advweek4.model.Student
import com.example.a160418034_advweek4.util.loadImage
import kotlinx.android.synthetic.main.student_list_item.view.*

class StudentListAdapter( val studentList:ArrayList<Student>):RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), ButtonDetailClicklistener{
    class StudentViewHolder(var view: StudentListItemBinding) : RecyclerView.ViewHolder(view.root)

    fun updateStudentList(newStudentList:List<Student>)
    {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.student_list_item, parent, false)
        val view = DataBindingUtil.inflate<StudentListItemBinding>(inflater, R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)

    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.view.student =studentList[position]
        holder.view.listener = this
    //        holder.view.txtid.text = studentList[position].id
//        holder.view.txtName.text = studentList[position].name
//
//        holder.view.imageView.loadImage(studentList[position].photoUrl,
//            holder.view.progressBar)
//
//        holder.view.btnDetail.setOnClickListener{
//            val action = StudentListFragmentDirections.actionStudentdetail(studentList[position].id.toString())
//            Navigation.findNavController(it).navigate(action)
//        }
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onButtonDetailClick(v: View) {
        val action = StudentListFragmentDirections.actionStudentdetail(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }

}