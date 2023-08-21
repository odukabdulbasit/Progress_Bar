package com.example.progressbar

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private var progressStatus = 0
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar) // Initialize the ProgressBar

        // Simulate progress updates
        simulateProgress()
    }

    private fun simulateProgress() {
        Thread(Runnable {
            while (progressStatus < 100) {
                progressStatus += 5 // Increment progress
                handler.post {
                    progressBar.progress = progressStatus // Update progress bar
                    if (progressStatus >= 100) {
                        progressBar.visibility = View.GONE // Hide progress bar
                        // Do other actions after loading completes
                    }
                }
                try {
                    Thread.sleep(500) // Simulate some work being done
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }).start()
    }
}

