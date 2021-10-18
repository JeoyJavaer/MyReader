package master.kotlin.readerpro.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created on 2021/10/18.
 * Description
 * Others
 */
@Entity(tableName = "txtToRules")
data class TxtToRule(
    @PrimaryKey var id:Long =System.currentTimeMillis(),
    var name:String ="",
    var rule:String="",
    var serialNumber:Int=-1,
    var enable:Boolean =true
)
