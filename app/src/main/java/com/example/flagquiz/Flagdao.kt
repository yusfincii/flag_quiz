package com.example.flagquiz

class Flagdao {

    // this function returns an arraylist which holds 10 flag object has id, name and image name
    fun getRandom10Flag(db:DatabaseHelper) : ArrayList<Flag>{
        val db = db.writableDatabase

        // arraylist which holds objects from flag class
        val flagList = ArrayList<Flag>()

        val cursor = db.rawQuery("SELECT * FROM flag ORDER BY RANDOM() LIMIT 10", null)

        while (cursor.moveToNext()){
            // creating new object which hold informations come from database
            val flag = Flag(
                cursor.getInt(cursor.getColumnIndexOrThrow("flag_id")),
                cursor.getString(cursor.getColumnIndexOrThrow("flag_name")),
                cursor.getString(cursor.getColumnIndexOrThrow("flag_image"))
            )
            // adding to arraylist
            flagList.add(flag)
        }
        return flagList
    }

    // this function returns an arraylist which holds 3 string flag name
    fun getRandomWrongOptions(db:DatabaseHelper, rightChoiseFlagName:String) : ArrayList<String>{
        val db = db.writableDatabase

        val optionList = ArrayList<String>()
        // 3 wrong flag name selection process
        val cursor = db.rawQuery("SELECT flag_name FROM flag WHERE flag_name != $rightChoiseFlagName" +
                " ORDER BY RANDOM() LIMIT 3", null)

        while (cursor.moveToNext()){
            val option = cursor.getString(cursor.getColumnIndexOrThrow("flag_name"))
            // adding to arraylist
            optionList.add(option)
        }
        db.close()
        return optionList
    }
}