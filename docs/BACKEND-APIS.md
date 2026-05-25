# Backend API Documentation

## User Management

### Create User
**POST** `/api/users`
```json
{
  "email": "user@example.com",
  "name": "John Doe"
}
```
Response: 201 Created

### Get User
**GET** `/api/users/{id}`
Response: 200 OK

### Update Navbar Position
**PUT** `/api/users/{id}/navbar-position`
```json
{
  "position": "TOP"
}
```
Response: 200 OK

### Get All Users
**GET** `/api/users`
Response: 200 OK with user list
