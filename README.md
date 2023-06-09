# tour-planner

--- RESTS ---

USER

GET /getAllUsers -> fetch all users
GET /user/{id} -> fetch user by ID

CITY

GET /cityByName/{name} -> fetch city by its name
GET /getAllCities -> fetch all cities
GET /city/{ID} -> fetch city by its ID
POST /city/{name} -> try to insert a new city with the given name, if it can geocode it
PUT /city/{ID} -> update city by ID  (body should be of the new city name)
DELETE /city/{ID} -> delete city by ID

ITINERARY

GET /getAllItineraries -> fetch all itineraries
GET /itinerary/{ID} -> fetch itinerary by its ID
POST /itinerary/ -> insert a new itinerary (body should be of type "Itinerary")
PUT /itinerary/{ID} -> update itinerary by ID (body should be of type "Itinerary")
DELETE /itinerary/{ID} -> delete itinerary by ID

STAY

GET /getAllStays -> fetch all stays
GET /stay/{ID} -> fetch stay by its ID
POST /stay/ -> insert a new stay (body should be of type "Stay")
PUT /stay/{ID} -> update stay by ID (body should be of type "Stay")
DELETE /stay/{ID} -> delete stay by ID

--- TYPES ---

User:
{
  number ID (used for REST calls)
  String name
  String surnname
  String email
  List<Itinerary> itineraries (see below for type)
}

Itinerary:
{
  number ID (used for REST calls, do not use while inserting)
  number userID (every itinerary should be linked to a user)
  number statusID (see below for accepted values)
  String description
  Date startDate
  Date endDate
  List<Stay> stays (see below for type)
}

Stay:
{
  number ID (used for REST calls, do not use while inserting)
  number itineraryID (every stay should be linked to an itinerary)
  number cityID (every stay should be linked to a city)
  String description
  Date stayDate
  WeatherCondition weatherCondition
}

WeatherCondition:
{
  number stayID (every stay shall be linked to a stay)
  number temperature
  number humidity
  number skyConditionID (see below for accepted values)
}

--- ENUMS ---

Status:
1: Not ready (less than 2 stays)
2: Ready (at least 2 stays)
3: Confirmed (at least 2 stays, confirmed by the user)
4: Done (at least 2 stays, endDate before current day)

Sky Condition:
1: Clear
2: Overcast
3: Rain
4: Snow