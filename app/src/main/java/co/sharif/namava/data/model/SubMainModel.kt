package co.sharif.namava.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Datum (
    val uri: String ?= null,
    val name: String ?= null,
    val description: String? = null,
    val type: String? = null,
    val link: String? = null,
    val duration: Long? = null,
    val width: Long? = null,
    //val language: Any? = null,
    val height: Long? = null,
   // val embed: EmbedClass? = null,
    val createdTime: String? = null,
   // val modifiedTime: String? = null,
    //val releaseTime: String? = null,
   // val contentRating: List<ContentRating>? = null,
    //val license: Any? = null,
    //val privacy: Privacy,
    val pictures: Pictures? = null,
   // val tags: List<Any?>? = null,
    //val stats: Stats,
   // val categories: List<Any?>? = null,
   // val uploader: Uploader? = null,
    val metadata: DatumMetadata? = null,
   // val user: User? = null,
   // val app: App? = null,
   // val status: DatumStatus,
   // val resourceKey: String,
   // val upload: Upload,
    //val transcode: Transcode,
   // val isPlayable: Boolean,
   // val hasAudio: Boolean
):Parcelable

data class App (
    val name: String,
    val uri: String
)

enum class ContentRating {
    Safe,
    Unrated
}

data class EmbedClass (
    val html: String,
    val badges: Badges
)

@Parcelize
data class Badges (
    val hdr: Boolean,
    val live: Live,
   // val staffPick: StaffPick,
    val vod: Boolean,
    val weekendChallenge: Boolean
):Parcelable

@Parcelize
data class Live (
    val streaming: Boolean,
    val archived: Boolean
):Parcelable

data class StaffPick (
    val normal: Boolean,
    val bestOfTheMonth: Boolean,
    val bestOfTheYear: Boolean,
    val premiere: Boolean
)

@Parcelize
data class DatumMetadata (
    val connections: PurpleConnections,
    val interactions: PurpleInteractions,
    @Json(name = "is_vimeo_create")
    val isVimeoCreate: Boolean,
    @Json(name = "is_screen_record")
    val isScreenRecord: Boolean
):Parcelable

@Parcelize
data class PurpleConnections (
    val comments: Albums,
    val credits: Albums,
    val likes: Albums,
    val pictures: Albums,
    val texttracks: Albums,
    val related: Recommendations,
    val recommendations: Recommendations,
    val albums: Albums? = null,
    @Json(name = "available_albums")
    val availableAlbums: Albums? = null,
    @Json(name = "available_channels")
    val availableChannels: Albums? = null,
   // val versions: Versions? = null
):Parcelable

@Parcelize
data class Albums (
    val uri: String,
    //val options: List<AlbumsOption>,
    val total: Long
):Parcelable

enum class AlbumsOption {
    Get,
    Patch,
    Post
}

@Parcelize
data class Recommendations (
    val uri: String,
   // val options: List<AlbumsOption>
):Parcelable

data class Versions (
    val uri: String,
    val options: List<AlbumsOption>,
    val total: Long,
    val currentURI: String,
    val resourceKey: String
)

@Parcelize
data class PurpleInteractions (
    val watchlater: Like,
    val like: Like,
    val report: Report,
    @Json(name = "update_privacy_to_public")
    val updatePrivacyToPublic: Boolean
):Parcelable

@Parcelize
data class Like (
    val uri: String,
   // val options: List<LikeOption>,
    val added: Boolean,
    //val addedTime: Any? = null
):Parcelable

enum class LikeOption {
    Delete,
    Get,
    Put
}

@Parcelize
data class Report (
    val uri: String,
   // val options: List<AlbumsOption>,
  //  val reason: List<Reason>
):Parcelable

enum class Reason {
    BadVideos,
    CausesHarm,
    Creepy,
    Csam,
    Harassment,
    Impersonation,
    InappropriateAvatar,
    InappropriateJobPost,
    IncorrectRating,
    NotPlayingNice,
    Pornographic,
    Ripoff,
    Spam,
    Spammy
}

@Parcelize
data class Pictures (
    val uri: String? = null,
    val active: Boolean,
    //val type: PicturesType,
    val sizes: List<Size>,
    @Json(name = "resource_key")
    val resourceKey: String,
    @Json(name = "default_picture")
    val defaultPicture: Boolean
):Parcelable

@Parcelize
data class Size (
    val width: Long,
    val height: Long,
    val link: String,
//    val linkWithPlayButton: String? = null
):Parcelable

data class Stats (
    val plays: Long? = null
)

data class Upload (
    val status: UploadStatus,
    val link: Any? = null,
    val uploadLink: Any? = null,
    val completeURI: Any? = null,
    val form: Any? = null,
    val approach: Any? = null,
    val size: Any? = null,
    val redirectURL: Any? = null
)

enum class UploadStatus {
    Complete,
    Error
}

data class Uploader (
    val pictures: Pictures
)

data class User (
    val uri: String,
    val name: String,
    val link: String,
    val capabilities: Capabilities,
    val location: Location,
    val gender: String,
    val bio: String? = null,
    val shortBio: String? = null,
    val createdTime: String,
    val pictures: Pictures,
    val websites: List<Website>,
    val metadata: UserMetadata,
   // val locationDetails: LocationDetails,
    val skills: List<Any?>,
    val availableForHire: Boolean,
    val canWorkRemotely: Boolean,
    val resourceKey: String,
    val account: Account
)

enum class Account {
    Basic,
    Enterprise,
    LivePremium,
    Pro
}

data class Capabilities (
    val hasLiveSubscription: Boolean,
    val hasEnterpriseLihp: Boolean,
    val hasSvvTimecodedComments: Boolean
)

enum class Location {
    Empty,
    Pristina
}

data class LocationDetails (
    val formattedAddress: Location,
    val latitude: Any? = null,
    val longitude: Any? = null,
    val city: Any? = null,
    val state: Any? = null,
    val neighborhood: Any? = null,
    val subLocality: Any? = null,
    val stateISOCode: Any? = null,
    val country: Any? = null,
    val countryISOCode: Any? = null
)

@Parcelize
data class UserMetadata (
    val connections: FluffyConnections,
    val interactions: FluffyInteractions
):Parcelable

@Parcelize
data class FluffyConnections (
    val albums: Albums,
    val appearances: Albums,
    val channels: Albums,
    val feed: Recommendations,
    val followers: Albums,
    val following: Albums,
    val groups: Albums,
    val likes: Albums,
    val membership: Recommendations,
    val moderatedChannels: Albums,
    val portfolios: Albums,
    val videos: Albums,
    val shared: Albums,
    val pictures: Albums,
    val foldersRoot: Recommendations,
    val teams: Albums,
    val permissionPolicies: Albums? = null
):Parcelable

@Parcelize
data class FluffyInteractions (
    val follow: Like,
    val block: Like,
    val report: Report
):Parcelable

@Parcelize
data class Website (
    val uri: String,
    val name: String? = null,
    val link: String,
    val type: String,
    val description: String? = null
):Parcelable

@Parcelize
data class Paging (
    val next: String?=null,
    val previous: String? = null,
    val first: String,
    val last: String
):Parcelable
