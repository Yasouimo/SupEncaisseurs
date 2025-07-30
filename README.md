# RADEM Supervisor Management System 

A comprehensive web application built with Spring Boot to streamline supervisor management across RADEM agencies in Meknes, Morocco.

## 📋 Project Overview

This web application was developed during an internship at RADEM Meknes (August 2024 - September 2024) under the supervision of **Mr. AMAR Ahmed**. The system provides a complete solution for managing supervisors, agencies, and users with role-based access control.

## 🔧 Technologies Used

- **Backend**: Spring Boot 3.x, Spring Security, Spring Data JPA
- **Frontend**: HTML5, CSS3, JavaScript, Bootstrap 4, Thymeleaf
- **Database**: MySQL
- **Build Tool**: Maven
- **Authentication**: Spring Security with BCrypt password encoding
- **UI Framework**: Bootstrap 4 with Font Awesome icons

## 🌟 Key Features

### Multi-Role Authentication System
- **Admin**: Complete system management including user and agency management
- **Manager**: Supervisor management and reporting capabilities
- **User**: View-only access to supervisors and agencies

### Core Functionalities
- **User Management**: Create, edit, and delete user accounts with role assignments
- **Supervisor Management**: Track and manage supervisor activities across agencies
- **Agency Management**: Organize and maintain agency information
- **Profile Management**: Users can update their profiles and change passwords
- **Responsive Design**: Mobile-friendly interface with modern UI components

### Security Features
- Role-based access control (RBAC)
- CSRF protection
- Password encryption with BCrypt
- Session management
- Custom access denied handling

## 🖼️ Application Screenshots

### Login Page
![login](https://github.com/user-attachments/assets/bae160cd-faf1-4cea-9c83-1f8c9e934c35)

### Admin Dashboard
![accueil](https://github.com/user-attachments/assets/7fbe97d4-429e-4df8-8b5c-2927267d6a5f)

### Manager Dashboard
![main manager](https://github.com/user-attachments/assets/5e61d347-adf2-4ad8-81de-bfa61a4384a2)

### User Dashboard
![main user](https://github.com/user-attachments/assets/4a86559f-2c01-49c7-a129-5b2de1c43ba5)

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/Yasouimo/SupEncaisseurs.git
   cd springboot-radem
   ```

2. **Configure Database**
   - Create a MySQL database named `sup`
   - Update `application.properties` with your database credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/sup
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the Application**
   - Navigate to `http://localhost:8080`
   - Use the login credentials for your assigned role

## 🐳 Docker Deployment

The application is available as a Docker image for quick deployment.

### Quick Start with Docker

1. **Pull and run the Docker image**
   ```bash
   docker run -d -p 8080:8080 --name radem-supervisor yasouimo14/radem-supervisor:latest
   ```

2. **Access the application**
   - Navigate to `http://localhost:8080`
   - The application will be ready to use immediately

### Docker Commands

- **Stop the container**: `docker stop radem-supervisor`
- **Start the container**: `docker start radem-supervisor`
- **Remove the container**: `docker rm radem-supervisor`

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/superviseur/springboot/
│   │   ├── Agence/          # Agency management
│   │   ├── Role/            # Role management
│   │   ├── Superviseur/     # Supervisor management
│   │   ├── User/            # User management
│   │   ├── config/          # Security configuration
│   │   └── constants/       # Application constants
│   └── resources/
│       ├── static/css/      # Stylesheets
│       ├── static/images/   # Images and assets
│       └── templates/       # Thymeleaf templates
```

## 🔐 User Roles and Permissions

### Admin Role
- ✅ Manage all users (create, edit, delete)
- ✅ Manage agencies (create, edit, delete)
- ✅ View user management dashboard
- ❌ Cannot manage supervisors directly

### Manager Role
- ✅ Manage supervisors (create, edit, delete)
- ✅ View supervisor dashboard with filtering
- ✅ Access profile management
- ❌ Cannot manage users or agencies

### User Role
- ✅ View supervisors and agencies
- ✅ Filter supervisors by agency
- ✅ Access profile management
- ❌ No create/edit/delete permissions

## 🛡️ Security Implementation

- **Authentication**: Form-based login with custom success handlers
- **Authorization**: Method-level security with role-based access
- **Password Security**: BCrypt encoding for all user passwords
- **CSRF Protection**: Enabled for all state-changing operations
- **Session Management**: Secure session handling with proper logout

## 📊 Database Schema

The application uses the following main entities:
- **Users**: User authentication and profile information
- **Roles**: System roles (Admin, Manager, User)
- **Supervisors**: Supervisor information and agency assignments
- **Agencies**: Agency details and supervisor relationships

## 🔄 Development Workflow

1. **Backend Development**: Built with Spring Boot following MVC architecture
2. **Frontend Integration**: Thymeleaf templates with Bootstrap for responsive design
3. **Security Implementation**: Spring Security for authentication and authorization
4. **Database Integration**: JPA/Hibernate for data persistence
5. **Testing**: Unit tests included for core functionality

## 👥 Team

**Developer**: BELLMIR Yahya  
**Supervisor**: Mr. AMAR Ahmed  
**Organization**: RADEM Meknes  
**Duration**: August 2024 - September 2024  

## 📄 License

This project was developed as part of an internship program at RADEM Meknes.

## 🤝 Acknowledgments

Special thanks to **Mr. AMAR Ahmed** for his guidance and supervision throughout the development of this application, and to the RADEM Meknes team for providing the opportunity to work on this project.

---

*This application demonstrates the implementation of a scalable, secure, and maintainable web application using modern Spring Boot technologies with a focus on user experience and system security.*

