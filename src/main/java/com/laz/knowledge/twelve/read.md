#GSON
验证gson.fromJson转换日期字符串时，不同机器时区问题，到时日期对象不一样

Gson底层采用如下代码进行代时区的字符串日期转换，系统需要设定对应时区  
DateFormat enUsFormat     = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.US);   
Date d = enUsFormat.parse("Apr 21, 2020 4:25:04 PM");  
System.out.println(d);  

[linux时区设置](https://blog.csdn.net/stpice/article/details/89487273)