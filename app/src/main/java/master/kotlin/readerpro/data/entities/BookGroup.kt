package master.kotlin.readerpro.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created on 2021/10/18.
 * Description
 * Others
 */
@Parcelize
@Entity(tableName = "book_groups")
data class BookGroup (
    @PrimaryKey val groupId:Int =0b1,
            var groupName:String,
                    var order:Int =0
): Parcelable