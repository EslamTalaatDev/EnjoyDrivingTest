package com.io.enjoydrivintask.ui.base

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<DB : ViewDataBinding, VM : ViewModel>(
    private val viewModelClass: Class<VM>
) : AppCompatActivity() {

    protected abstract val layoutId: Int
    protected abstract val viewModelVariableId: Int

    lateinit var binding: DB
    lateinit var viewModel: VM

    protected abstract fun provideViewModelFactory(): ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this, layoutId)
        viewModel = createViewModel()

        binding.lifecycleOwner = this
        binding.setVariable(viewModelVariableId, viewModel)

    }

    private fun createViewModel(): VM {
        return ViewModelProvider(this, provideViewModelFactory())[viewModelClass]
    }

    inline fun <reified T : ViewModel> createViewModelFactory(noinline viewModelBlock: () -> T) =
        ViewModelFactory { viewModelBlock() }

    class ViewModelFactory(private val viewModelBlock: () -> ViewModel) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
             return this.viewModelBlock() as T
        }
    }


}

