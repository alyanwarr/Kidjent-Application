package com.example.apanoo.apanoo_agile;

import java.util.ArrayList;

/**
 * Created by APANOO on 8/12/2016.
 */

public class ArrayOfPictures {
    private static ArrayList<QPicture> array;
    private static QPicture pic;
   static  ArrayList<QPicture> GetArrayofPictures()
    {   array=new ArrayList<>();
        PicturesInitialize();
        return array;
    }
    private static void PicturesInitialize()
    {
        initialize("Tree","android.resource://com.example.apanoo.apanoo_agile/"+R.drawable.tree);
        initialize("Strawberry","android.resource://com.example.apanoo.apanoo_agile/"+R.drawable.strawberry);
        initialize("Pen","android.resource://com.example.apanoo.apanoo_agile/"+R.drawable.pen);
        initialize("Pencil","android.resource://com.example.apanoo.apanoo_agile/"+R.drawable.pencil);
        initialize("House","android.resource://com.example.apanoo.apanoo_agile/"+R.drawable.house);
        initialize("Heart","android.resource://com.example.apanoo.apanoo_agile/"+R.drawable.heart1);
        initialize("Money","android.resource://com.example.apanoo.apanoo_agile/"+R.drawable.money);
        initialize("Apple","android.resource://com.example.apanoo.apanoo_agile/"+R.drawable.apple);
    }
    private static void  initialize(String name,String Path){
        pic=new QPicture(name,Path);
        array.add(pic);
    }
}
