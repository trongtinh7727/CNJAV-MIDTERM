<!-- Improved compatibility of back to top link: See: https://github.com/trongtinh7727/CNJAV-MIDTERM/pull/73 -->
<a name="readme-top"></a>

<!-- PROJECT LOGO -->
<br />
<div align="center">

  <h3 align="center">Java Midterm Project</h3>

  <p align="center">
    Excellent Experience
    <br />
    <a href="https://github.com/trongtinh7727/CNJAV-MIDTERM"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/trongtinh7727/CNJAV-MIDTERM">View Demo</a>
    ·
    <a href="https://github.com/trongtinh7727/CNJAV-MIDTERM/issues">Report Bug</a>
    ·
    <a href="https://github.com/trongtinh7727/CNJAV-MIDTERM/issues">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#software-development-principles-patterns-and-practices">Software Development Principles, Patterns, and Practices</a>
    </li>
    <li>
      <a href="#code-structure">Code Structure</a>
    </li>
    <li>
      <a href="#running-the-application">Running the Application</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
        <li><a href="#usage">Usage</a></li>
      </ul>
    </li>
    <li>
      <a href="#apis">APIs:</a>
    </li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project
This is a web application that allows users to browse a catalog of products and filter them by category, brand, color, and price range. It was built using Java, Spring Boot, Thymeleaf, and Bootstrap.

<p align="right">(<a href="#about-the-project">back to top</a>)</p>



### Built With

* [![Docker][Docker.com]][Docker-url]
* [![Spring][Spring.io]][Spring-url]
* [![Bootstrap][Bootstrap.com]][Bootstrap-url]
* [![JQuery][JQuery.com]][JQuery-url]

<p align="right">(<a href="#about-the-project">back to top</a>)</p>

## Software Development Principles, Patterns, and Practices

The following principles, patterns, and practices were applied in the development of this application:


- `Dependency Injection`: Spring Boot's dependency injection feature was used to manage the dependencies between the components of the application.
- `Model-View-Controller (MVC)`: The application follows the MVC architectural pattern, with the model representing the data, the view displaying the data, and the controller handling the user's input and updating the model and view accordingly.
- `Repository Pattern`: The application uses a repository pattern to abstract the persistence layer and provide a cleaner and more modular architecture.

## Code Structure
The code for the application is organized into the following packages:

- `com.iiex.javamidterm`: Contains the main class of the application.
- `com.iiex.javamidterm.API`: Contains the API class of the application.
- `com.iiex.javamidterm.config`: Contains the class to config `spring security` of the application.
- `com.iiex.javamidterm.Controller`: Contains the controllers that handle the HTTP requests and responses.
- `com.iiex.javamidterm.Model`: Contains the model classes for the application.
- `com.iiex.javamidterm.Repository`: Contains the repository interfaces for the application.
- `com.iiex.javamidterm.Security`: Contains the class to config `spring security` of the application.
- `com.iiex.javamidterm.Service`: Contains the service classes for the application.

Additon: 
- `Docs.ERD`: Contains the Entity-relationship diagram for the database
- `Docs.Activity-diagram`: Contains the Entity-relationship diagram for the database

## Running the Application


### Prerequisites

* docker install: [Docker Engine](https://www.docker.com/products/docker-desktop)

### Installation

1. Run Docker Engine
2. Run compose at the root of project
   ```sh
   docker-compose up -d
   ```
3. Import database: 
- Go to phpMyAdmin at `http://localhost:8090` with username/password: root/admin
- Chosse database name `manager` and import `data.sql`
4. Open the project in an IDE such as Eclipse or IntelliJ.
5. Build the project using Maven or your IDE's build tools.
6. Run the main class (`Application`).
7. Open a web browser and go to `http://localhost:8080`.
8. To close this app. Stop project from IDE and run this cmd at the root of project
```sh
   docker-compose down
```

<p align="right">(<a href="#about-the-project">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
### Usage
Demo account:
- Admin(username/pass): admin/a
- Customer(username/pass): user/a

Manager page: `/admin`

Customer page: `/`

Flow activity diagram [here](./Docs/Activity%20diagram/)


<p align="right">(<a href="#about-the-project">back to top</a>)</p>

# Entity-relationship diagram:
![erd][erd]

# APIs:
    
## Order
### GET /api/orders
![getAllOrder][getAllOrder]

### GET /api/orders/{id}
![getOrder][getOrder]

### POST /api/orders
![addOrder][addOrder]

### PUT /api/orders/{id}
![updateOrder][updateOrder]

### DELETE /api/orders/{id}
![deleteOrder][deleteOrder]


## Product

### GET /api/products

![getAllProduct][getAllProduct]

### GET /api/products/{id}
![getProduct][getProduct]

### GET /api/products/{id}
![getProduct][getProduct]

### POST /api/products
![addProduct][addProduct]

### PUT /api/products/filter
![updateProduct][updateProduct]

Request Parameters:

| Parameter	| Type	| Description| 
|:-----------:|:-------:|------------|
| category	| string	| The category to filter by| 
| brand	| string	| The brand to filter by| 
| colors	| string	| The color to filter by| 
| minPrice	| int	| The minimum price to filter by| 
| maxPrice	| int	| The maximum price to filter by| 

![filterProduct][filterProduct]


[Spring.io]: https://img.shields.io/badge/SpringBoot-white?style=for-the-badge&logo=spring&logoColor=green
[Spring-url]: https://spring.io/

[Docker.com]: https://img.shields.io/badge/docker-ffffff?style=for-the-badge&logo=docker&logoColor=blue
[Docker-url]: https://www.docker.com

[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com 

[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com


[getAllProduct]: ./Docs/APIs/Product/getAllProduct.png
[getProduct]: ./Docs/APIs/Product/getAProduct.png
[addProduct]: ./Docs/APIs/Product/AddProduct.png
[updateProduct]: ./Docs/APIs/Product/UpdateProduct.png
[filterProduct]: ./Docs/APIs/Product/filter.png

[getAllOrder]: ./Docs/APIs/Order/getAll.png
[getOrder]: ./Docs/APIs/Order/getAOrder.png
[addOrder]: ./Docs/APIs/Order/addOrder.png
[updateOrder]: ./Docs/APIs/Order/updateOrder.png
[deleteOrder]: ./Docs/APIs/Order/deleteOrder.png
[erd]: ./Docs/ERD/ERD.png

