package com.sample.nytimes.model

import com.google.gson.annotations.SerializedName

data class MostViewed(
    val status: String,
    val copyright: String,
    val num_results: Int,
    val results: List<Result>
)

data class Result(
    val adx_keywords: String,
    val asset_id: Long,
    val byline: String,
    val column: String,
    val count_type: String,
    val email_count: Int,
    val eta_id: Int,
    val id: Long,
    val media: List<Media>,
    val nytdsection: String,
    val published_date: String,
    val section: String,
    val source: String,
    val subsection: String,
    val title: String,
    val type: String,
    val updated: String,
    val uri: String,
    val url: String
)

data class Media(
    val approved_for_syndication: Int,
    val caption: String,
    val copyright: String,
    @SerializedName("media-metadata")
    val media_metadata: List<MediaMetadata>,
    val subtype: String,
    val type: String
)

data class MediaMetadata(
    val format: String,
    val height: Int,
    val url: String,
    val width: Int
)