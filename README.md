**Finance Simple calculator**

This is a simple Spring Boot application that provides REST API 
to calculate financial data. It consists of two layers: controller and
calculation service. All API operations use BigDecimal type to avoid
of incorrect rounding for financial computation. Input parameters
validation is based on spec _JSR 303: Bean Validation_. Both app layers
are covered by Unit tests. Application uses Swagger framework to document 
and easily explore API endpoints. To avoid of computation each time when 
the same parameters are passed to Service layer all methods use Spring cache. 

**Getting started**

- Import project as a Maven project to your IDE.
- Run _CalculatorApplication.class_ from you IDE
or run Maven command _mvn spring-boot:run_
- Open in browser Swagger Web console http://localhost:8080/swagger-ui.html
- _application.properties_ file contains _max.digits_ property that define a max length of result value

**API Description**

API provides four endpoints to calculate BigDecimal numbers:
- /add/{a}/{b}/{c}
- /subtract/{a}/{b}/{c}
- /multiply/{a}/{b}/{c}
- /divide/{a}/{b}


Please use _Swagger_ console to explore API http://localhost:8080/swagger-ui.html
