package com.example.marvelcharacters

import com.google.gson.annotations.Expose

class Thumbnail(

    @Expose
    val path: String,
    @Expose
    val extension: String
)