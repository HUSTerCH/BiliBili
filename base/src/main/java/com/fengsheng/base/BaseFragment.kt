package com.fengsheng.base

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.airbnb.mvrx.Mavericks
import com.airbnb.mvrx.MavericksView

abstract class BaseFragment<VB : ViewBinding> : Fragment(), MavericksView {

    private var _binding: VB? = null

    // This property is only valid between onCreateView and onDestroyView.
    protected val views: VB
        get() = _binding!!

    private var loadingDialog: DialogFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getBinding(inflater, container)
        return views.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeViewEvents()
        loadData()
    }

    abstract fun getBinding(inflater: LayoutInflater, container: ViewGroup?): VB

//    fun setImmerseLayout(window: Window, view: View, needExpandHeight: Boolean = true) {
//        val statusHeight = DisplayUtil.getStatusBarHeight(window, requireContext())
//        view.setPadding(
//            view.paddingStart,
//            statusHeight + view.paddingTop,
//            view.paddingEnd,
//            view.paddingBottom
//        )
//        if (needExpandHeight) {
//            view.layoutParams.apply {
//                height += statusHeight + view.paddingTop
//            }
//        }
//    }

    protected fun setArguments(args: Parcelable? = null) {
        arguments = args.toMvRxBundle()
    }

    fun showLoading(dialog: DialogFragment) {
        loadingDialog = dialog
        loadingDialog?.show(childFragmentManager, "LoadingDialog")
    }

    fun dismissLoading() {
        loadingDialog?.dismiss()
        loadingDialog = null
    }

    override fun invalidate() {

    }

    open fun initView() {

    }

    open fun loadData() {

    }

    open fun observeViewEvents() {

    }

}

fun Parcelable?.toMvRxBundle(): Bundle? {
    return this?.let { Bundle().apply { putParcelable(Mavericks.KEY_ARG, it) } }
}