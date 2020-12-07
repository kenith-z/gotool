# gotool

# use 
 +  System.out.println(HttpRequest.sendPost("http://localhost:8080/login","username=123","password=123"));
 +  System.out.println(HttpRequest.sendGet("https://baidu.com"));
 
 +  System.out.println(Ping.ping());
 +  System.out.println(Ping.ping("baidu.com"));
 +  System.out.println(Ping.ping(3000));
 +  System.out.println(Ping.ping("baidu.com",3000));
 
 +  DateTimeUtils.setZone("+8");
 +  System.out.println(DateTimeUtils.getCurrentDateTimeStr());
 +  System.out.println(DateTimeUtils.getCurrentDateStr());
 +  System.out.println(DateTimeUtils.getCurrentTimeStr());
 +  System.out.println(DateTimeUtils.getCurrentTimeSssStr());

