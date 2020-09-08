package master.kotlin.readerpro.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created on 2020/9/8.
 * Description
 * Others
 */
fun <T:ViewModel>AppCompatActivity.getViewModel(clazz:Class<T>)=
    ViewModelProvider(this).get(clazz)


fun <T:ViewModel>Fragment.getViewModel(clazz: Class<T>)=
    ViewModelProvider(this).get(clazz)


fun <T:ViewModel>Fragment.getViewModelOfActivity(clazz: Class<T>)=
    ViewModelProvider(requireActivity()).get(clazz)
