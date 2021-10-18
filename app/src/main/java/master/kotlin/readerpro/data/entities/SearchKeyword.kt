package master.kotlin.readerpro.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created on 2021/10/18.
 * Description
 * Others
 */
@Parcelize
@Entity(tableName = "search_keywords",indices = [(Index(value = ["word"], unique = true))])
data class SearchKeyword(
    @PrimaryKey
    var word:String="",
    var usage:Int=1,
    var lastUseTime:Long=System.currentTimeMillis(),
):Parcelable
