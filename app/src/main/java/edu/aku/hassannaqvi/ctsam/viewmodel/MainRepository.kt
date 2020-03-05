package edu.aku.hassannaqvi.ctsam.viewmodel

import android.content.Context
import android.content.Intent
import edu.aku.hassannaqvi.ctsam.contracts.FamilyMembersContract
import edu.aku.hassannaqvi.ctsam.core.MainApp
import edu.aku.hassannaqvi.ctsam.ui.sections.SectionK2Activity
import kotlinx.coroutines.*

class MainRepository(val context: Context, val item: MutableList<FamilyMembersContract>) {

    init {

        val result = GlobalScope.async { populateList() }

        runBlocking {
            context.startActivity(Intent(context, SectionK2Activity::class.java))
        }

    }

    private suspend fun populateList() = withContext(Dispatchers.IO) {
        MainApp.mwraChildrenAnthro = Triple(item.map { it.serialno.toInt() }, item.map { it.name }, item.map { it })
    }
}