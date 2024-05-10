This is a microservice project having 5 different microservices - 
1 - UserService
2 - HotelService
3 - RatingService
4 - GatewayService
5 - ConfigService

User, Hotel and Rating service provide main functionality and all the crud operations.

Gateway service acts as a common entry point for above 3 services.

Config service is used to externalize the repetitive configurations. and then use them in different microservices.
