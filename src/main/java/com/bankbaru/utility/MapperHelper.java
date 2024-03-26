package com.bankbaru.utility;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MapperHelper {
    /*
     * untuk mengambil nilai sebuah field didalam object dengan tipe data object
     * nilai field yang didapat itu data type nya object.
     * Nama field tidak diketahui, diambil dengan menggunakan urutan index field
     */
    public static Object getFieldValue(Object object, Integer index){
        return ((Object[])object)[index];
    }

    /*
     * Dapatkan nilai sebuah field dari Object object, dengan  menggunakan nama fieldnya.
     * Field yang di return bernilai Object.*/
    public static Object getFieldValue(Object object, String fieldName){
        try{
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            var value = field.get(object);
            return value;
        }catch (Exception exception){
        }
        return null;
    }

    /*
     * Set nilai field pada sebuah object dengan tipe data Object.
     * Hanya bila nama fieldnya diketahui.
     * */
    public static void setFieldValue(Object object, String fieldName, Object newValue){
        try{
            var type = object.getClass();
            Field field = type.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, newValue);
        }catch (Exception exception){
        }
    }


    public static Boolean getBooleanField(Object object, Integer index){
        try{
            return (Boolean) ((Object[])object)[index];
        }catch (Exception exception){
            return null;
        }
    }

    public static <T> Boolean getBooleanField(T object, String fieldName){
        try{
            return (Boolean) getFieldValue(object, fieldName);
        }catch (Exception exception){
            return null;
        }
    }

    public static Integer getIntegerField(Object object, Integer index){
        try{
            return (Integer)((Object[])object)[index];
        }catch (Exception exception){
            return null;
        }
    }

    public static <T> Integer getIntegerField(T object, String fieldName){
        try{
            return (Integer) getFieldValue(object, fieldName);
        }catch (Exception exception){
            return null;
        }
    }

    public static Long getLongField(Object object, Integer index){
        try{
            return (Long)((Object[])object)[index];
        }catch (Exception exception){
            return null;
        }
    }

    public static <T> Long getLongField(T object, String fieldName){
        try{
            return (Long) getFieldValue(object, fieldName);
        }catch (Exception exception){
            return null;
        }
    }

    public static Double getDoubleField(Object object, Integer index){
        try{
            return (Double)((Object[])object)[index];
        }catch (Exception exception){
            return null;
        }
    }

    public static <T> BigDecimal getBigDecimalField(T object, String fieldName){
        try{
            return (BigDecimal) getFieldValue(object, fieldName);
        }catch (Exception exception){
            return null;
        }
    }

    public static BigDecimal getBigDecimalField(Object object, Integer index){
        try{
            return (BigDecimal) ((Object[])object)[index];
        }catch (Exception exception){
            return null;
        }
    }

    public static <T> Double getDoubleField(T object, String fieldName){
        try{
            return (Double) getFieldValue(object, fieldName);
        }catch (Exception exception){
            return null;
        }
    }

    public static <T> String getStringField(T object, String fieldName){
        try{
            return getFieldValue(object, fieldName).toString();
        }catch (Exception exception){
            return null;
        }
    }

    public static String getStringField(Object object, Integer index){
        try{
            return ((Object[])object)[index].toString();
        }catch (Exception exception){
            return null;
        }
    }

    public static <T> LocalDate getLocalDateField(T object, String fieldName){
        try{
            var stringValue = getStringField(object, fieldName);
            var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            var date = LocalDate.parse(stringValue, formatter);
            return date;
        }catch (Exception exception){
            return null;
        }
    }

    public static LocalDate getLocalDateField(Object object, Integer index){
        try{
            var stringValue = getStringField(object, index);
            var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            var date = LocalDate.parse(stringValue, formatter);
            return date;
        }catch (Exception exception){
            return null;
        }
    }

    public static List<String> getAllFieldNames(Object object){
        List<String> names = new ArrayList<>();
        var fields = object.getClass().getDeclaredFields();
        for(var field : fields){
            names.add(field.getName());
        }
        return names;
    }


    public static <T> T mappingObject(Object origin, Class<T> destinationType) throws Exception{
        var destination = destinationType.getDeclaredConstructor().newInstance();
        var fieldNames = getAllFieldNames(origin);
        for(var fieldName : fieldNames){
            try{
                var value = getFieldValue(origin, fieldName);
                setFieldValue(destination, fieldName, value);
            } catch (Exception exception){
                continue;
            }
        }
        return destination;
    }

    public static Integer getTotalSize(Object object){
        var size = 0;
        try{
            if(object instanceof Collection){
                size = ((Collection<?>) object).size();
            }
        }catch (Exception exception){
        }
        return size;
    }
}
