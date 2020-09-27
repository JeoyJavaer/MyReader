package master.kotlin.readerpro.ui.main

import android.app.Application
import kotlinx.coroutines.asCoroutineDispatcher
import master.kotlin.readerpro.base.BaseViewModel
import master.kotlin.readerpro.data.entities.rule.Book
import master.kotlin.readerpro.help.AppConfig
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArraySet
import java.util.concurrent.Executors

class MainViewModel(app: Application) :BaseViewModel(app) {
    private var threadCount =AppConfig.threadCount
    private  var upTocPool=Executors.newFixedThreadPool(threadCount).asCoroutineDispatcher()
    private val updateList = CopyOnWriteArraySet<String>()
    private val bookMap=ConcurrentHashMap<String, Book>()



}