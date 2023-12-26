package br.com.mrocigno.alicization.common.di

import org.koin.core.KoinApplication
import org.koin.core.module.Module

object KoinModules {

    private val modules: MutableList<Module> = mutableListOf()

    fun addModules(vararg modules: Module) =
        this.modules.addAll(modules)

    fun KoinApplication.initialize() {
        println("ASDASDASDASDA ${modules.size}")
        modules(modules)
    }
}