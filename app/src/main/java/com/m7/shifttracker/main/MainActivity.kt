package com.m7.shifttracker.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonParser
import com.m7.shifttracker.R
import com.m7.shifttracker.Shift
import com.m7.shifttracker.addShift.AddShiftFragment
import com.m7.shifttracker.databinding.ActivityMainBinding
import java.io.BufferedReader

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var addShiftFragment: AddShiftFragment

    private val shifts = mutableListOf<Shift>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        getBaseList()

        binding.apply {
            val shiftAdapter = ShiftAdapter(shifts)

            rvShifts.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = ShiftAdapter(shifts)
            }

            addShiftFragment = AddShiftFragment {
                shiftAdapter.addItem(it)
                rvShifts.post {
                    rvShifts.smoothScrollToPosition(0)
                }
            }

            btnAddShift.setOnClickListener {
                supportFragmentManager
                    .beginTransaction()
                    .replace(mainRoot.id, addShiftFragment)
                    .addToBackStack(null)
                    .commit()

                rvShifts.scrollToPosition(shifts.lastIndex)
            }
        }
    }

    private fun getBaseList() {
        assets.open("shifts.json").bufferedReader().use(BufferedReader::readText).also {
            Log.d("check_Json", it)
            shifts.addAll(
                JsonParser.parseString(it).asJsonObject["shifts"].asJsonArray.map {
                    it.asJsonObject.let { shiftObj ->
                        Shift(
                            shiftObj["role"].asString,
                            shiftObj["name"].asString,
                            shiftObj["start_date"].asString,
                            shiftObj["end_date"].asString,
                            shiftObj["color"].asString
                        )
                    }
                }
            )
        }
    }
}