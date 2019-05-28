# Order Report

Now that you can create orders in the database, we'll want to report on them. Create a new controller `ReportController`. It will use `@RestController` similar to the `CartController`. Add a method to return a report to summarize orders per hour and include the total sales during that hour.

The report will be returned in JSON by the server and viewed in Angular using a similar approach to your store front.

Some Java classes you may use: [Date](https://docs.oracle.com/javase/10/docs/api/java/util/Date.html), [HashMap](https://docs.oracle.com/javase/10/docs/api/java/util/HashMap.html), [BigDecimal](https://docs.oracle.com/javase/10/docs/api/java/math/BigDecimal.html).

## Extra Credit

Try building your report by querying your data using JDBC. You'll use the `JdbcTemplate`. You'll need to find the table and field names using MySQL Workbench or phpMyAdmin.
