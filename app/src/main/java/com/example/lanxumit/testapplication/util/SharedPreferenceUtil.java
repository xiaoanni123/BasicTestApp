package com.example.lanxumit.testapplication.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class SharedPreferenceUtil {

    public String fileName = "StoreTxT";

    /**
     * @param mContext context
     * @param mKey     need store key
     * @param object   need store value
     * @param fileName need store fileName
     */
    public static void put(Context mContext, String mKey, Object object, String fileName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (object instanceof String) {
            edit.putString(mKey, (String) object);
        } else if (object instanceof Boolean) {
            edit.putBoolean(mKey, (Boolean) object);
        } else if (object instanceof Integer) {
            edit.putInt(mKey, (Integer) object);
        } else if (object instanceof Float) {
            edit.putFloat(mKey, (Float) object);
        } else if (object instanceof Long) {
            edit.putLong(mKey, (Long) object);
        } else {
            edit.putString(mKey, object.toString());
        }
        SharedPreferenceCompact.apply(edit);
    }


    /**
     * @param mContext context
     * @param mKey     need store key
     * @param object   need store value
     * @param fileName need store fileName
     */
    public static Object get(Context mContext, String mKey, Object object, String fileName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        if (object instanceof String) {
            return sharedPreferences.getString(mKey, (String) object);
        } else if (object instanceof Boolean) {
            return sharedPreferences.getBoolean(mKey, (Boolean) object);
        } else if (object instanceof Integer) {
            return sharedPreferences.getInt(mKey, (Integer) object);
        } else if (object instanceof Float) {
            return sharedPreferences.getFloat(mKey, (Float) object);
        } else if (object instanceof Long) {
            return sharedPreferences.getLong(mKey, (Long) object);
        }
        return null;
    }
    /**
     * @param mContext context
     * @param mKey     need store key
     * @param fileName need store fileName
     * @return  isExist
     */
    public static boolean contains(Context mContext, String mKey, String fileName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sharedPreferences.contains(mKey);
    }
    /**
     * @param mContext context
     * @param mKey     need store key
     * @param fileName need store fileName
     */
    public static void remove(Context mContext, String mKey, String fileName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(mKey);
        SharedPreferenceCompact.apply(edit);
    }
    /**
     * @param mContext context
     * @param fileName need store fileName
     */
    public static void clearAll(Context mContext, String fileName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.clear();
        SharedPreferenceCompact.apply(edit);
    }
    /**
     * @param mContext context
     * @param fileName need store fileName
     * @return Map<String, ?>  all map -key -value
     */
    public static Map<String, ?> getAll(Context mContext, String fileName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Map<String, ?> all = sharedPreferences.getAll();
        return all;
    }

    private static class SharedPreferenceCompact {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射找到 apply method
         *
         * @return
         */
        private static Method findApplyMethod() {
            try {
                Class editorClass = SharedPreferences.Editor.class;
                return editorClass.getMethod("apply");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 如果找到apply(),则用此提交数据，否者使用commit()提交。
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            editor.commit();
        }
    }

    /**
     * 因为commit方法是同步的，并且我们很多时候的commit操作都是UI线程中，毕竟是IO操作，尽可能异步；
     *
     * 所以我们使用apply进行替代，apply异步的进行写入
     */

    /**
     * need create save Object --getObject() & putObject()
     */
}
