package br.com.mrocigno.alicization.common

import android.content.Context
import androidx.annotation.CallSuper
import androidx.startup.Initializer
import br.com.mrocigno.alicization.common.di.KoinModules
import org.koin.core.module.Module

abstract class ModuleInitializer : Initializer<Unit> {

    abstract val modules: Array<Module>

    @CallSuper
    override fun create(context: Context) {
        KoinModules.addModules(*modules)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> =
        mutableListOf()
}