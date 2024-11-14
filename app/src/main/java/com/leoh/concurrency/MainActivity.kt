package com.leoh.concurrency

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.delay

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private val coroutineCompletableFutureWorker = CoroutineCompletableFutureWorkers.Main
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        button = findViewById(R.id.button)
        button.setOnClickListener {
            val task =
                coroutineCompletableFutureWorker.execute {
                    for (i in 0 until 100) {
                        countUp(i)
                        sleep()
                    }
                }
            task.whenComplete { result, throwable -> Log.e(TAG, "Result: $result", throwable) }
        }
    }

    private suspend fun sleep() {
        delay(1000)
    }

    private suspend fun countUp(count: Int) {
        Log.d(TAG, "Cancelable count down task: $count")
    }

    override fun onPause() {
        super.onPause()
        coroutineCompletableFutureWorker.cancelChildren()
    }
}
