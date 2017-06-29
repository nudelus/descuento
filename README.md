# discuento 


## Overview
Discuento is a customer discount handler service:

1. If the user is an employee of the store, he gets a 30% discount
2. If the user is an affiliate of the store, he gets a 10% discount
3. If the user has been a customer for over 2 years, he gets a 5% discount.
4. For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45 as a discount).
5. The percentage based discounts do not apply on groceries.
6. A user can get only one of the percentage based discounts on a bill.

## Installation

### Prerequisites
To build from source, you need the following installed (and available in your $PATH):

* [Java 8+](http://java.oracle.com)

* [Apache Maven 3 or greater](http://maven.apache.org/)

### Building
After cloning the project, you can build it from source with this command:
```
mvn verify
```
You can install it into your local Maven repository:
```
mvn install
```

## Test coverage report
During the building process maven creates a coverage report with [JaCoCo](www.eclemma.org/jacoco). The generated report can be found in the site folder: _target/site/jacoco/index.html_
