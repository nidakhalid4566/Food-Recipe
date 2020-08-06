package com.example.foodrecipe;

import android.provider.BaseColumns;

public class DatabbaseContract {
    public DatabbaseContract() {}

    /* Inner class that defines the table contents */
    public static abstract class f2 implements BaseColumns {
        public static final String TABLE_NAME = "Res";
        public static final String COL_RES_NAME = "Name";
        public static final String COL_INGREDIENTS = "Ingredients";
        public static final String COL_DESCRIPTION = "Descripton";
        public static final String COL_LINK = "Link";
        public static final String COL_CATEGORY= "Category";
        public static final String Col_IMG = "image_url";



    }
    public static abstract class f21 implements BaseColumns {
        public static final String TABLE_NAME = "login";
        public static final String COL_NAME = "UserName";
        public static final String COL_Pass = "Password";
        public static final String COL_FeedBack = "Feedback";
        public static final String COL_Rating = "Rating";
    }
}

