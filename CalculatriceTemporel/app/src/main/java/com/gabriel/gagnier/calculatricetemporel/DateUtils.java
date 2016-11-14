package com.gabriel.gagnier.calculatricetemporel;

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
        int i1 = cal1.get(time);
        int i2 = cal2.get(time);
        if(i1<i2)
            return i2-i1;
        return i1-i2;
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
}