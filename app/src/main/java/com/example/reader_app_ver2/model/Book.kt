package com.example.reader_app_ver2.model

data class Book(
    //なんでここはvarかuserがvalなのは一度設定された値は変更されないから
    var id:String? = null,
    var title:String? = null,
    var author:String? = null,
    var notes:String? = null,
)
