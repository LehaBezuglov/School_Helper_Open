package ru.lehabezuglov.schoolhelper

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import ru.lehabezuglov.stconf.R
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        textView.setText(
                    "1 - 8:30 - 9:10  \n" +
                    "2 - 9:20 - 10:00 \n" +
                    "3 - 10:20 - 11:00\n" +
                    "4 - 11:20 - 12:00\n" +
                    "5 - 12:10 - 12:50\n" +
                    "6 - 13:00 - 13:40\n" +
                    "7 - 13:50 - 14:30"
        )

        fun SRU(){
            cab.setText("Кабинет: ${SR.Cabinet}")
            FNO.setText("ФИО: ${SR.Name}")
            pred.setText("Предмет: ${SR.Lesson}")
            Floort.setText("Этаж: ${SR.Floor}")
            Rec.setText("Рекреация: ${SR.Recration}")
        }

        SRU()

        fun search(cab: String) {
            val minput = InputStreamReader(assets.open("database.csv"))
            val reader = BufferedReader(minput)

            val bufferedReader = reader
            val csvParser = CSVParser(bufferedReader, CSVFormat.DEFAULT)
            for (csvRecord in csvParser) {
                val Cabinet = csvRecord.get(0);
                val Name = csvRecord.get(1);
                val Lesson = csvRecord.get(2);
                var Floor = csvRecord.get(3);
                var Recration = csvRecord.get(4);
                if (Cabinet == cab) {
                    SR.Cabinet = Cabinet.toString()
                    SR.Name = Name.toString()
                    SR.Lesson = Lesson.toString()
                    SR.Floor = Floor.toString()
                    SR.Recration = Recration.toString()
                    return

                }

                if (Name == cab) {
                    SR.Cabinet = Cabinet.toString()
                    SR.Name = Name.toString()
                    SR.Lesson = Lesson.toString()
                    SR.Floor = Floor.toString()
                    SR.Recration = Recration.toString()
                    return

                }
            }

            Toast.makeText(
                applicationContext,
                "Ничего не найдено!\nПример: Яшин Л.И",
                Toast.LENGTH_LONG
            ).show()
        }

        button.setOnClickListener() {
            var cab = ET2.text.toString()
            search(cab)
            SRU()
        }
    }
}