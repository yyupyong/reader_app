package com.example.reader_app_ver2.screens.login

data class LoadingState(val status: Status,val message:String?=null){

    companion object{
        val IDLE = LoadingState(Status.IDLE)
        val LOADING = LoadingState(Status.LOADING)
        val FAILED = LoadingState(Status.FAILED)
        val SUCCESS = LoadingState(Status.SUCCESS)
    }

    enum class Status{
        LOADING,
        SUCCESS,
        FAILED,
        IDLE,
    }

}
