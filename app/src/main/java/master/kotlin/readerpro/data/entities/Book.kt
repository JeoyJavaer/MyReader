package master.kotlin.readerpro.data.entities.rule

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import master.kotlin.readerpro.constant.BookType
import master.kotlin.readerpro.help.AppConfig


@Parcelize
@Entity(tableName = "books",indices = [Index(value = ["name","author"],unique = true)])
data class Book(
    @PrimaryKey
    override var bookUrl: String = "",//详情页url，本地图书的地址
    var tocUrl: String = "",
    var origin: String = BookType.local,        //书源URL，默认booktpye.local
    var originaName: String = "",  // 书源名称 或者本书籍据名称
    var name: String = "",     //书籍名称 通过书源获取的
    var author: String = "",  //书籍作者
    override var kind: String? = null,        //分类信息 ，书源获取
    var cutomTag: String? = null,             //分裂信息 用户修改
    var coverUrl: String? = null,              //封面URl 书源获取
    var customCoverUrl: String? = null,        //封面URL 用户修改
    var intro: String? = null,                  //书籍简介 书源获取
    var customIntro: String? = null,            //书籍简介  用户修改
    var charset: String? = null,               //本地书籍的自定义字符集名称
    var type: Int = 0,                          // 0 text   1 audio
    var group: Int = 0,                         //自定义分组序号
    var latestChapterTitle: String? = null,     //最近章节标题
    var latestChapterTime: Long = System.currentTimeMillis() ,//最新章节标题更新时间
    var lastCheckTime: Long = System.currentTimeMillis(),    //最近一次更新书籍信息的时间
    var latestCheckCount: Int = 0,                  //最近一次新章节的数量
    var totalChapterNum: Int = 0,                   //书籍目录总数
    var durChapterTitle:String?=null,   //当前章节名称
    var durChapterIndex:Int=0,          //当前章节索引
    var durChapterPos:Int=0,    //当前阅读进度，（首行字符的索引位置）
    var durChapterTime:Long=System.currentTimeMillis(),         //最近一次阅读书籍的时间
    override var wordCount: String?=null,
    var canUpdate:Boolean=true, //更新书籍信息
    var  order:Int=0,         //手动排序后的序号
    var originOrder:Int=0,      //书源排序
    var useReplaceRule:Boolean=AppConfig.replaceEnableDefault,  //正文使用净化替换规则
    var variable:String?=null               //自定义书籍变量信息 ，用于书源规则检索书籍信息
    ) :Parcelable,BaseBook  {

    fun  isLocalBook():Boolean{
        return origin==BookType.local
    }

    fun isLocalTxt():Boolean{
        return  isLocalBook()  && originaName.endsWith(".txt")
    }

    fun  isEpub():Boolean{
        return  originaName.endsWith(".epub",true)
    }

    fun isOnLineTxt():Boolean{
        return !isLocalBook() && type==0
    }

    override fun equals(other: Any?): Boolean {
        if (other is Book){
            return bookUrl ==bookUrl
        }
        return false
    }

    override fun hashCode(): Int {
        return bookUrl.hashCode()
    }

    override val variableMap by lazy {
        GSON.from
    }



































}