package com.notes.whatstoday.dashboard.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.notes.whatstoday.base.InjectUtils
import com.notes.whatstoday.dashboard.R
import com.notes.whatstoday.dashboard.di.component.DaggerDashboardActivityComponent
import com.notes.whatstoday.data.DatabaseService
import javax.inject.Inject

class DashboardActivity : AppCompatActivity() {

    @Inject
    lateinit var databaseService: DatabaseService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        DaggerDashboardActivityComponent
            .builder()
            .baseComponent(InjectUtils.provideBaseComponent(applicationContext))
            .build()
            .inject(this)
        Log.d("DaggerSample_FeatureTwo", databaseService.toString())

    }
}
