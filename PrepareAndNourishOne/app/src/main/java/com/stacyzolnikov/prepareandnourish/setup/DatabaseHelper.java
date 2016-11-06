package com.stacyzolnikov.prepareandnourish.setup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.stacyzolnikov.prepareandnourish.R;
import com.stacyzolnikov.prepareandnourish.recipe_lists.Recipe;
import com.stacyzolnikov.prepareandnourish.unsigned_users.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stacyzolnikov on 10/20/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String TAG = "DatabaseHelper";

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Prepare_Nourish_DB";

    //Table names for Both Home(unsigned and signed), categories, etc
    public static final String CATEGORY_TABLE_NAME = "categoryList";
    public static final String RECIPE_TABLE_NAME = "recipeList";


    //Columns for the category list
    public static final String COL_ID_CATEGORY = "_id";
    public static final String COL_CATEGORY_NAME = "categoryName";
    public static final String COL_CATEGORY_PHOTO = "categoryPhoto";
    public static final String[] CATEGORY_COLUMNS = {COL_ID_CATEGORY, COL_CATEGORY_NAME, COL_CATEGORY_PHOTO};

    //Columns for recipes list
    public static final String COL_ID_RECIPE = "_id";
    public static final String COL_RECIPE_NAME = "recipeName";
    public static final String COL_RECIPE_PHOTO = "recipePhoto";
    public static final String COL_RECIPE_CATEGORY = "category";
    public static final String[] RECIPE_COLUMNS = {COL_ID_RECIPE, COL_RECIPE_NAME, COL_RECIPE_PHOTO};



    private static DatabaseHelper instance;
    public static synchronized DatabaseHelper getInstance(Context context){
        if (instance == null)
            instance = new DatabaseHelper(context.getApplicationContext());
        return instance;
    }


    public DatabaseHelper(Context context) {
        super(context, "db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(SQL_CREATE_ENTRIES_CATEGORY);
        db.execSQL(SQL_CREATE_ENTRIES_RECIPE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES_CATEGORY);
        db.execSQL(SQL_DELETE_ENTRIES_RECIPE);

    }

    //Creating the entries for tables
    private static final String SQL_CREATE_ENTRIES_CATEGORY =
            "CREATE TABLE " + CATEGORY_TABLE_NAME +
                    " (" +
                    COL_ID_CATEGORY + " INTEGER PRIMARY KEY," +
                    COL_CATEGORY_NAME + " TEXT," +
                    COL_CATEGORY_PHOTO + " INT" +
                    ")";

    private static final String SQL_CREATE_ENTRIES_RECIPE =
            "CREATE TABLE " + RECIPE_TABLE_NAME +
                    " (" +
                    COL_ID_RECIPE + " INTEGER PRIMARY KEY," +
                    COL_RECIPE_NAME + " TEXT," +
                    COL_RECIPE_PHOTO + " INT," +
                    COL_RECIPE_CATEGORY + " TEXT" +
                    ")";

    //Deleting tables
    private static final String SQL_DELETE_ENTRIES_CATEGORY = "DROP TABLE IF EXISTS " + CATEGORY_TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_RECIPE = "DROP TABLE IF EXISTS " + RECIPE_TABLE_NAME;

    //Adding rows to tables
    public void insertRowCategory (Category category){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_CATEGORY_NAME, category.getCategoryName());
        values.put(COL_CATEGORY_PHOTO, category.getCategoryPhoto());
        db.insert(CATEGORY_TABLE_NAME, "",values);
        db.close();
    }
    public void insertRowRecipe (Recipe recipe){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_RECIPE_NAME, recipe.getRecipeName());
        values.put(COL_RECIPE_PHOTO, recipe.getRecipePhoto());
        values.put(COL_RECIPE_CATEGORY, recipe.getRecipeCategory());
        db.insert(RECIPE_TABLE_NAME, "", values);
        db.close();

    }

    //Search Queries
    //Main categories
    public List<Category> getCategories(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM categoryList", null);
        List<Category> category = new ArrayList<>();
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                int id = cursor.getInt(cursor.getColumnIndex(COL_ID_CATEGORY));
                String categoryName = cursor.getString(cursor.getColumnIndex(COL_CATEGORY_NAME));
                int categoryPhoto = cursor.getInt(cursor.getColumnIndex(COL_CATEGORY_PHOTO));

                category.add(new Category(id,categoryName,categoryPhoto));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return category;
    }

    public List<Recipe> getBreakfast(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM recipeList WHERE "+ COL_RECIPE_CATEGORY + " LIKE " + "'%Breakfast%'", null);
        //Cursor cursor = db.rawQuery("SELECT * FROM recipeList ", null);
        List<Recipe> recipe = new ArrayList<>();
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                int id = cursor.getInt(cursor.getColumnIndex(COL_ID_RECIPE));
                String recipeName = cursor.getString(cursor.getColumnIndex(COL_RECIPE_NAME));
                int recipePhoto = cursor.getInt(cursor.getColumnIndex(COL_RECIPE_PHOTO));
                recipe.add(new Recipe(id, recipeName, recipePhoto));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return recipe;
    }
    public List<Recipe> getEntrees(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM recipeList WHERE " + COL_RECIPE_CATEGORY + " LIKE " + "'%Entrees%'",null);
        List<Recipe> recipe = new ArrayList<>();
        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                int id = cursor.getInt(cursor.getColumnIndex(COL_ID_RECIPE));
                String recipeName = cursor.getString(cursor.getColumnIndex(COL_RECIPE_NAME));
                int recipePhoto = cursor.getInt(cursor.getColumnIndex(COL_RECIPE_PHOTO));
                recipe.add(new Recipe(id, recipeName, recipePhoto));
                cursor.moveToNext();
            }

        }
        cursor.close();
        return recipe;
    }
    public List<Recipe> getSalads(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM recipeList WHERE " + COL_RECIPE_CATEGORY + " LIKE " + "'%Salads%'", null);
        List<Recipe> recipe = new ArrayList<>();
        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                int id = cursor.getInt(cursor.getColumnIndex(COL_ID_RECIPE));
                String recipeName = cursor.getString(cursor.getColumnIndex(COL_RECIPE_NAME));
                int recipePhoto = cursor.getInt(cursor.getColumnIndex(COL_RECIPE_PHOTO));
                recipe.add(new Recipe(id, recipeName, recipePhoto));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return recipe;
    }
    public List<Recipe> getDrinks(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM recipeList WHERE " + COL_RECIPE_CATEGORY + " LIKE " + "'%Drinks%'", null);
        List<Recipe> recipe = new ArrayList<>();
        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                int id = cursor.getInt(cursor.getColumnIndex(COL_ID_RECIPE));
                String recipeName = cursor.getString(cursor.getColumnIndex(COL_RECIPE_NAME));
                int recipePhoto = cursor.getInt(cursor.getColumnIndex(COL_RECIPE_PHOTO));
                recipe.add(new Recipe(id, recipeName, recipePhoto));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return recipe;
    }
    //Check to see if Recipe list is empty
    public boolean checkDatabase(){
        Boolean rowExists;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM recipeList ", null);
        if (cursor.moveToFirst()){

            if (cursor.getInt(0)==0){
                rowExists = true;
            }
        }
        return true ;
    }

    //Adding categories
    public void addCategories(){
        SQLiteDatabase db = getWritableDatabase();
        Category category1 = new Category("Breakfast", R.drawable.breakfast);
        Category category2 = new Category("Entrees", R.drawable.entrees);
        Category category3 = new Category("Drinks",R.drawable.drinks);
        Category category4 = new Category("Salads",R.drawable.salad);
        Category category5 = new Category("Snacks",R.drawable.snacks);
        Category category6 = new Category("Ferments",R.drawable.ferments);
        Category category7 = new Category("Sides",R.drawable.sides);
        Category category8 = new Category("Soups",R.drawable.soup);
        Category category9 = new Category("Sweets",R.drawable.sweets);

        insertRowCategory(category1);
        insertRowCategory(category2);
        insertRowCategory(category3);
        insertRowCategory(category4);
        insertRowCategory(category5);
        insertRowCategory(category6);
        insertRowCategory(category7);
        insertRowCategory(category8);
        insertRowCategory(category9);

    }


    public void addRecipe(){
        SQLiteDatabase db = getWritableDatabase();
        Recipe recipe1 = new Recipe("Fall Inspired Steel Cut Oatmeal", R.drawable.steelcutoatmeal, "Breakfast");
        Recipe recipe2 = new Recipe("Breakfast Kale Egg Cups", R.drawable.kale_egg_cups, "Breakfast");
        Recipe recipe3 = new Recipe("Soaked Oatmeal Breakfast Bars (Sugar Free)", R.drawable.oatmeal_bars, "Breakfast");
        Recipe recipe4 = new Recipe("Spinach and Sardine Breakfast Salad with Hollandaise Sauce", R.drawable.spinach_sardine_breakfast_salad, "Breakfast");
        Recipe recipe5 = new Recipe("Banana and Egg Pancakes", R.drawable.banana_egg_pancakes, "Breakfast");
        Recipe recipe6 = new Recipe("Pizza Eggs", R.drawable.pizza_eggs, "Breakfast");
        Recipe recipe7 = new Recipe("Butternut Rice Kasha", R.drawable.butternut_rice_kasha, "Breakfast");
        //Entrees
        Recipe recipe8 = new Recipe("Acorn Squash Burrito Bowls", R.drawable.entrees, "Entrees");
        Recipe recipe9 = new Recipe("Baked Gluten-Free Chicken Tenders", R.drawable.entrees, "Entrees");
        Recipe recipe10 = new Recipe("Soy Free Spicy Teriyaki Chicken Wings", R.drawable.entrees, "Entrees");
        Recipe recipe11 = new Recipe("25 Delicious Lettuce Wraps and Other Fillings", R.drawable.entrees, "Entrees");
        Recipe recipe12 = new Recipe("Parmesan and Kale Turkey Meatlets", R.drawable.entrees, "Entrees");
        Recipe recipe13 = new Recipe("Gluten Free Fried Fish Sticks", R.drawable.entrees, "Entrees");
        Recipe recipe14 = new Recipe("Bacon Wrapped Potato Wedges", R.drawable.entrees, "Entrees");
        Recipe recipe15 = new Recipe("Classic Stuffed Cabbage Leaves", R.drawable.entrees, "Entrees");
        Recipe recipe16 = new Recipe("The When, How and Why of Aspic", R.drawable.entrees, "Entrees");
        Recipe recipe17 = new Recipe("Lagman-Lamb Uzbek Soup", R.drawable.entrees, "Entrees");
        Recipe recipe18 = new Recipe("Lamb and Kale Skillet Dinner", R.drawable.entrees, "Entrees");
        Recipe recipe19 = new Recipe("How to Cook Beef Tongue + Simple Tongue Appetizer", R.drawable.entrees, "Entrees");
        Recipe recipe20 = new Recipe("Shurpa - Middle Eastern Lamb Soup", R.drawable.entrees, "Entrees");

        //Salads
        Recipe recipe21 = new Recipe("Pan Browned Sweet Potato & Quinoa Salad", R.drawable.salad, "Salads");
        Recipe recipe22 = new Recipe ("Nourishing Beet Root Salad", R.drawable.salad, "Salads");
        Recipe recipe23 = new Recipe("Beef Tongue Summer Salad", R.drawable.salad, "Salads");
        Recipe recipe24 = new Recipe ("Cashew Garlic Zucchini Pinwheels", R.drawable.salad, "Salads");
        Recipe recipe25 = new Recipe("Cod Liver, Rice & Eggs Lettuce Wraps", R.drawable.salad, "Salads");
        Recipe recipe26 = new Recipe ("Refreshing Turnip & Dill Salad", R.drawable.salad, "Salads");
        Recipe recipe27 = new Recipe("Scalloped Butternut Squash Wtih Thyme & Caramelized Onions", R.drawable.salad, "Salads");
        Recipe recipe28 = new Recipe ("Autumnal Chia Fruit Salad Recipe", R.drawable.salad, "Salads");
        Recipe recipe29 = new Recipe("Cajun-Seasoned Sweet Potato Fries", R.drawable.salad, "Salads");
        Recipe recipe30 = new Recipe ("Beet-Up Cabbage Salad Recipe", R.drawable.salad, "Salads");
        Recipe recipe31 = new Recipe("Garlic Roasted Eggplant & Tomato Appetizer Recipe", R.drawable.salad, "Salads");

        //Drinks
        Recipe recipe32 = new Recipe ("Nutrient Dense Kale Pineapple Smoothie", R.drawable.nutrient_dense_pineapple_smoothie, "Drinks");
        Recipe recipe33 = new Recipe("Plum Nectar Drink", R.drawable.plum_nectar_drink, "Drinks");
        Recipe recipe34 = new Recipe ("Detoxifying Infused Water Combinations", R.drawable.detoxifying_infused_drinks, "Drinks");
        Recipe recipe35 = new Recipe("Mouthwatering Coconut Herbal Milkshake", R.drawable.mouthwatering_milkshake, "Drinks");
        Recipe recipe36 = new Recipe ("Cleansing & Healthy Herbal Coffee", R.drawable.cleansing_and_herbal_coffee, "Drinks");
        Recipe recipe37 = new Recipe("Superfood Hot Chocolate Mix", R.drawable.superfood_hot_chocolate_mix, "Drinks");
        Recipe recipe38 = new Recipe ("Homemade Chocolate Syrup", R.drawable.homemade_chocolate_syrup, "Drinks");
        Recipe recipe39 = new Recipe("Anti-Inflammatory Turmeric Tea", R.drawable.anti_inflammatory_turmeric_tea, "Drinks");
        Recipe recipe40 = new Recipe ("Homemade Yogurt", R.drawable.homemade_yogurt, "Drinks");
        Recipe recipe41 = new Recipe("Real Food Punch - Made With Hibiscus", R.drawable.real_food_punch, "Drinks");

        //Snacks
        Recipe recipe42 = new Recipe("Rice & Green Onions Spring Roll", R.drawable.rice_and_green_onions_spring_rolls, "Snacks");
        Recipe recipe43 = new Recipe("Cajun-seasoned Sweet Potato Fries", R.drawable.cajun_seasones_sweet_potato_fries, "Snacks");
        Recipe recipe44 = new Recipe("Moldovskiy Rolled Cookies Recipe", R.drawable.moldovsky_cookies, "Snacks");
        Recipe recipe45 = new Recipe("Coconut Lime Pita Chips", R.drawable.coconut_lime_pita_chips, "Snacks");
        Recipe recipe46 = new Recipe("Matcha Pecan Marshmallows", R.drawable.matcha_pecan_marshmellows, "Snacks");
        Recipe recipe47 = new Recipe("Sea Salt & Vinegar Sweet Potato Chips", R.drawable.sea_salt_vinegar_chips, "Snacks");

        //Soups
        Recipe recipe48 = new Recipe("Bone Broth", R.drawable.bone_broth, "Soups");
        Recipe recipe49 = new Recipe("Okroshka - Cold Summer Soup", R.drawable.okroshka, "Soups");
        Recipe recipe50 = new Recipe("Mushroom & Bacon Potato Chowder", R.drawable.mushroom_bacon_potato_chowder, "Soups");
        Recipe recipe51 = new Recipe("White Soup With Potato, Kale & Ground Beef", R.drawable.mushroom_bacon_potato_chowder, "Soups");
        Recipe recipe52 = new Recipe("Easy Meatball Soup", R.drawable.easy_meatball_soup, "Soups");
        Recipe recipe53 = new Recipe("Green Borsch - Sorrel Soup", R.drawable.green_borsch, "Soups");
        Recipe recipe54 = new Recipe("Easy Nourishing Fish Soup", R.drawable.easy_nourishing_fish_soup, "Soups");
        Recipe recipe55 = new Recipe("Lagman - Lamb Uzbek Soup", R.drawable.lagmank_lamb_uzbek_soup, "Soups");
        Recipe recipe56 = new Recipe("Borsch - Classic Russian Beet Soup", R.drawable.borsch_classic_russian_beet_soup, "Soups");
        Recipe recipe57 = new Recipe("Shurpa - Middle Eastern Lamb Soup", R.drawable.shurpa_middle_eastern_lamb_soup, "Soups");


        insertRowRecipe(recipe1);
        insertRowRecipe(recipe2);
        insertRowRecipe(recipe3);
        insertRowRecipe(recipe4);
        insertRowRecipe(recipe5);
        insertRowRecipe(recipe6);
        insertRowRecipe(recipe7);
        insertRowRecipe(recipe8);
        insertRowRecipe(recipe9);
        insertRowRecipe(recipe10);
        insertRowRecipe(recipe11);
        insertRowRecipe(recipe12);
        insertRowRecipe(recipe13);
        insertRowRecipe(recipe14);
        insertRowRecipe(recipe15);
        insertRowRecipe(recipe16);
        insertRowRecipe(recipe17);
        insertRowRecipe(recipe18);
        insertRowRecipe(recipe19);
        insertRowRecipe(recipe20);
        insertRowRecipe(recipe21);
        insertRowRecipe(recipe22);
        insertRowRecipe(recipe23);
        insertRowRecipe(recipe24);
        insertRowRecipe(recipe25);
        insertRowRecipe(recipe26);
        insertRowRecipe(recipe27);
        insertRowRecipe(recipe28);
        insertRowRecipe(recipe29);
        insertRowRecipe(recipe30);
        insertRowRecipe(recipe31);
        insertRowRecipe(recipe32);
        insertRowRecipe(recipe33);
        insertRowRecipe(recipe34);
        insertRowRecipe(recipe35);
        insertRowRecipe(recipe36);
        insertRowRecipe(recipe37);
        insertRowRecipe(recipe38);
        insertRowRecipe(recipe39);
        insertRowRecipe(recipe40);
        insertRowRecipe(recipe41);
        insertRowRecipe(recipe42);
        insertRowRecipe(recipe43);
        insertRowRecipe(recipe44);
        insertRowRecipe(recipe45);
        insertRowRecipe(recipe46);
        insertRowRecipe(recipe47);
        insertRowRecipe(recipe48);
        insertRowRecipe(recipe49);
        insertRowRecipe(recipe50);
        insertRowRecipe(recipe51);
        insertRowRecipe(recipe52);
        insertRowRecipe(recipe53);
        insertRowRecipe(recipe54);
        insertRowRecipe(recipe55);
        insertRowRecipe(recipe56);
        insertRowRecipe(recipe57);


    }



}
