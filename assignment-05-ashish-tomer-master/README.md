# assignment-05-ashish-tomer

## Testing screenshot

![alt tag](https://raw.githubusercontent.com/ashishknoldus/assignment-05-ashish-tomer/master/scalaTestResult.png)
The main business logic methods have 100% and 92% method coverage in daoImpl and mysqlworker classes/objects.

I couldn't test my project in terminal with - <code>sbt clean test</code>
Because with this it was showing different statistics for every test run. Once it showed 8 test fail, then only 2 tests failed and then 6 tests failed along with various aborts!
I guess the problem was that scalatest (in terminal) run the test cases in no particular order and therefor couldn't find the correct values in mysql tables. On the other hand test runs in IDE didn't trouble at all.

## scalastyle output

![alt tag](https://raw.githubusercontent.com/ashishknoldus/assignment-05-ashish-tomer/master/scalaStyle.png)
There was this particular warning. I couldn't resolve it.

<b>Update : The warning has been resolved</b>

## log4j Logger

I coudn't find a particular documentation on internet and couldn't perform logging.
