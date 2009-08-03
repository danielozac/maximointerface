// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   Config.java

package cn.com.gsoft.maximo.common;

import java.util.*;

public class Config
{

    private static ResourceBundle resources = null;
    private static Map propertyMap = new HashMap();

    public Config()
    {
    }

    private static void getBundle()
    {
        if(resources == null)
            resources = ResourceBundle.getBundle("setting", Locale.getDefault());
    }

    public static String getAttribute(String name)
    {
        String value = (String)propertyMap.get(name);
        if(value == null)
        {
            getBundle();
            if(resources != null)
                try
                {
                    value = resources.getString(name);
                }
                catch(MissingResourceException exception)
                {
                    return null;
                }
        }
        return value;
    }

    public static Map getAttributesMap(String name)
    {
        Map map = (Map)propertyMap.get(name);
        if(map == null)
        {
            getBundle();
            try
            {
                String keyStr = resources.getString(name);
                List keys = null;
                if(keyStr != null)
                    keys = split(keyStr, ",");
                map = new HashMap();
                if(keys != null)
                {
                    for(int i = 0; i < keys.size(); i++)
                    {
                        String value = resources.getString(name + "." + keys.get(i));
                        map.put(keys.get(i), value);
                    }

                }
                propertyMap.put(name, map);
            }
            catch(MissingResourceException exception)
            {
                return null;
            }
        }
        return map;
    }

    public static Set getAttributesSet(String name)
    {
        Set set = (Set)propertyMap.get(name);
        if(set == null)
        {
            set = new HashSet();
            getBundle();
            try
            {
                String value = resources.getString(name);
                List ips = null;
                if(value != null)
                    ips = split(value, ",");
                if(ips != null)
                {
                    for(int i = 0; i < ips.size(); i++)
                        set.add(ips.get(i));

                }
                propertyMap.put(name, set);
            }
            catch(MissingResourceException exception)
            {
                return null;
            }
        }
        return set;
    }

    private static List split(String source, String tag)
    {
        List result = new ArrayList();
        int i = source.indexOf(tag);
        int l = tag.length();
        for(; i > 0; i = source.indexOf(tag))
        {
            result.add(source.substring(0, i));
            source = source.substring(i + l);
        }

        if(!source.equals(""))
            result.add(source);
        return result;
    }

    public static void remove()
    {
        resources = null;
        propertyMap.clear();
    }

    public static void main(String args[])
    {
        System.out.println(getAttribute("sessionOperator.class.name"));
    }

}
