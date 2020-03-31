package com.example.marvelcharacters

import com.google.gson.annotations.Expose

class Results(
    @Expose
    val id: Long,
    @Expose
    val name: String,
    @Expose
    val description: String,

    @Expose
    val title: String,
    @Expose
    val thumbnail: Thumbnail

)