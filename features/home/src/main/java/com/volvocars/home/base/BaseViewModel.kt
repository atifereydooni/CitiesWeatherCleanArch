package com.volvocars.home.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    private val jobs = HashMap<String, Job>()

    fun track(jobName: String? = null, block: suspend CoroutineScope.() -> Unit) {
        val job = viewModelScope.launch(context = Dispatchers.IO, block = block)
        jobs[jobName ?: job.hashCode().toString()] = job
    }

    fun removeAllJob() {
        jobs.forEach {
            it.value.cancel()
        }
        jobs.clear()
    }

}