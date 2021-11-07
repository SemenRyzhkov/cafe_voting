###### REST API using Hibernate/Spring-Boot without frontend.

Description:

Voting system for deciding where to have lunch.

2 types of users: admin and regular users
Admin can input a cafe and it's lunch menu of the day (2-5 items usually, just a dish name and price)
Menu changes each day (admins do the updates)
Users can vote on which cafe they want to have lunch at
Only one vote counted per user
If user votes again the same day:
If it is before 11:00 we assume that he changed his mind.
If it is after 11:00 then it is too late, vote can't be changed
Each restaurant provides a new menu each day.

###### Endpoints for user:

POST api/users - registration user account
{
    "name": "NewUser",
    "email": "newuser@yandex.ru",
    "password": "newUser"
}

POST api/auth/login - login user
{
    "email": "admin@yandex.ru",
    "password": "admin"
}

GET api/users/1 - get information about user

PUT api/users/user-edit/1 - update information about user
{
    "name": "UpdUser",
    "email": "upd@yandex.ru",
    "password": "update1"
}

DELETE api/users/1 - delete user account

GET api/cafes - get list all cafes

GET api/cafes/menu/1/by-date/cur-date - show today menu or yesterday menu if admin forgot to update of selected by id cafe

GET api/cafes/voting/makeVoice?cafeId=3 - make voice for the selected cafe

###### Endpoints for admin:

GET api/users - get list of user

GET api/cafes/2 - show the list of cafes owned by the user

GET api/cafes/2/8 - show cafe by id

POST api/cafes/2 - create cafe
{
    "cafeName": "new",
    "description": "newCafe",
    "homepage": "http://newCafe.ru"
}

PUT api/cafes/2/10 - update cafe by id
{
    "cafeName": "updCafe",
    "description": "updCafe",
    "homepage": "http://updCafe.ru"
}

DELETE api/cafes/2/10 - delete cafe by id

GET api/cafes/1/menu/1 - history menu of cafe by id for the all time

GET api/cafes/1/menu/1/by-date?date=2021-04-02 - history menu of cafe by id for the selected date

POST api/cafes/1/menu/1/dish - create dish for cafe by id
{
    "dish": "newDish",
    "price": 500
}

PUT api/cafes/1/menu/1/dish/13 - update dish by id and cafe id
{
    "dish": "updDish",
    "price": 1000
}

DELETE api/cafes/1/menu/1/dish/13 - delete dish by id and cafe id 

GET api/cafes/voting/1/history?cafeId=1 - history of voting for the selected cafe

GET api/cafes/voting/1/today?cafeId=1 - count of today voting for the selected cafe

