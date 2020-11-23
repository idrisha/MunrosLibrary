Munro Library Challenge
The API provides the following functionality:
? Filtering of search by hill category (i.e. Munro, Munro Top or either). If this information is
not provided by the user it should default to either. This should use the “post 1997”
column and if it is blank the hill should be always excluded from results.
? The ability to sort the results by height in meters and alphabetically by name. For both
options it should be possibly to specify if this should be done in ascending or descending
order.
? The ability to limit the total number of results returned, e.g. only show the top 10
? The ability to specify a minimum height in meters
? The ability to specify a maximum height in meters
? Queries may include any combination of the above features and none are mandatory.
The query results returns a list of items using JSON. Each item contains
the name, height in meters, hill category and grid reference (e.g. NN773308)

Notes:
? No external database and Munro data is held in memory.
? To load the CSV data into your system there is an endpoint
? No authentication or rate limiting to endpoints is done.

Swagger : http://localhost:8080/swagger-ui/index.html
Health : http://localhost:8080/actuator/health