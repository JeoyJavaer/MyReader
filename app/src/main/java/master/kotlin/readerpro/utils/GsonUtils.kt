package master.kotlin.readerpro.utils

import com.google.gson.*
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.attempt
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import kotlin.math.ceil


val GSON: Gson by lazy {
    GsonBuilder().registerTypeAdapter(object :TypeToken<Map<String?,Any?>?>(){}.type,MapDeserializerDoubleAsIntFix())
        .registerTypeAdapter(Int::class.java,IntJsonDeserializer())
        .disableHtmlEscaping()
        .setPrettyPrinting()
        .create()
}

inline  fun <reified  T> genericType():Type =object :TypeToken<T>(){}.type


inline  fun <reified  T>Gson.fromJsonObject(json:String?):T?{
    return  attempt {
        val  result:T? = fromJson(json, genericType<T>())
        result
    }.value
}

inline  fun <reified  T>Gson.fromJsonArray(json: String?):List<T>?{
    return  attempt {
        val  result:List<T>?= fromJson(json,ParameterizedTypeImpl(T::class.java))
        result
    }.value
}

class ParameterizedTypeImpl (private  val clazz: Class<*>):ParameterizedType{
    override fun getActualTypeArguments(): Array<Type> = arrayOf(clazz)

    override fun getRawType(): Type = List::class.java

    override fun getOwnerType(): Type?=null

}


class  IntJsonDeserializer :JsonDeserializer<Int?>{
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Int? {
        return  when{
            json.isJsonPrimitive ->{
                val prim  = json.asJsonPrimitive
                if (prim.isNumber) {
                    prim.asNumber.toInt()
                }else{
                    null
                }
            }
            else -> null
        }
    }

}

class MapDeserializerDoubleAsIntFix :JsonDeserializer<Map<String,Any?>?>{
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Map<String, Any?>? {

        return read(json) as? Map<String,Any?>
    }

    fun read(json:JsonElement):Any?{
        when{
            json.isJsonArray ->{
                val list:MutableList<Any?> = ArrayList()
                val arr =json.asJsonArray
                for (anArr in arr){
                    list.add(read(anArr))
                }
                return  list
            }

            json.isJsonObject ->{
                val map :MutableMap<String?,Any?> =LinkedTreeMap<String,Any>()
                val obj = json.asJsonObject
                val entrySet = obj.entrySet()
                for ((key,value) in entrySet){
                    map[key]= read(value)
                }
                return  map
            }

            json.isJsonPrimitive ->{
                val  prim = json.asJsonPrimitive
                when{
                    prim.isBoolean -> return  prim.asBoolean
                    prim.isString  -> return  prim.asString
                    prim.isNumber  -> {
                        val num :Number = prim.asNumber
                        return if (ceil(num.toDouble())==num.toLong().toDouble()){
                            num.toLong()
                        }else{
                            num.toDouble()
                        }
                    }
                }

            }
        }

        return null;
    }

}