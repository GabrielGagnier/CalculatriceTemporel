package com.gabriel.gagnier.calculatricetemporel.util;

import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by gagnier on 13/11/16.
 */

public class DateUtils {


    /**
     *
     * @param time definit l'operation d'addition (sur les jours, mois, annees [...])
     * @param date date a laquel on additionne
     * @param i entier que l'on additionne
     * @return la date en format dd/MM/yyyy
     * @throws Exception
     */
    private static String add(int time, String date, int i) throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(date));
        c.add(time, i);
        return format.format(c.getTime());
    }

    /**
     *
     * @param time donne l'echelle du delta (jours, mois, annees [...])
     * @param date1 premiere date a comparer
     * @param date2 seconde date a comparer
     * @return le delta entre date 1 et 2
     * @throws Exception
     */
    private static int delta(int time, String date1, String date2) throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(format.parse(date1));
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(format.parse(date2));


        int diffMonths = 0;
        int diffYears = 0;

        long timeCal1 = cal1.getTimeInMillis();
        long timeCal2 = cal2.getTimeInMillis();
        long difference = 0;

        if(timeCal1 < timeCal2){
            difference = timeCal2 - timeCal1;
            diffMonths = cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);
            diffYears = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
        }
        else{
            difference = timeCal1 - timeCal2;
            diffMonths = cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH);
            diffYears = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        }

        difference /= 86400000;

        switch(time){
            case Calendar.DATE: //JOURS
                return (int)difference;
            case Calendar.MONTH:
                return diffMonths + 12 * diffYears;
            case Calendar.YEAR:
                return diffYears;
        }

        return -1;
    }

    /**
     * Ajoute un nombre de jours a une date
     * @param date string representant une date sous la forme dd/MM/yyyy
     * @param i nombre de jour a ajouter
     * @return une date en string sous le format dd/MM/yyyy
     * @throws Exception
     */
    public static String addDays(String date, int i) throws Exception{
        return add(Calendar.DATE,date,i);
    }

    /**
     * Ajoute un nombre de mois a une date
     * @param date string representant une date sous la forme dd/MM/yyyy
     * @param i nombre de mois a ajouter
     * @return une date en string sous le format dd/MM/yyyy
     * @throws Exception
     */
    public static String addMonth(String date, int i) throws Exception{
        return add(Calendar.MONTH,date,i);
    }

    /**
     * Ajoute un nombre d'annees a une date
     * @param date string representant une date sous la forme dd/MM/yyyy
     * @param i nombre d'annees a ajouter
     * @return une date en string sous le format dd/MM/yyyy
     * @throws Exception
     */
    public static String addYears(String date, int i) throws Exception{
        return add(Calendar.YEAR,date,i);
    }

    /**
     *
     * @param date1
     * @param date2
     * @return la difference de jours entre date1 et date2
     * @throws Exception
     */
    public static int deltaDays(String date1, String date2)throws Exception{
        return delta(Calendar.DATE, date1, date2);
    }

    /**
     *
     * @param date1
     * @param date2
     * @return la difference de mois entre date1 et date2
     * @throws Exception
     */
    public static int deltaMonth(String date1, String date2)throws Exception{
        return delta(Calendar.MONTH, date1, date2);
    }

    /**
     *
     * @param date1
     * @param date2
     * @return la difference d'annees entre date1 et date2
     * @throws Exception
     */
    public static int deltaYear(String date1, String date2)throws Exception{
        return delta(Calendar.YEAR, date1, date2);
    }

    /**
     * revois la date du jour formater
     * @return
     * @throws Exception
     */
    public static String now(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        return format.format(c.getTime());
    }
}