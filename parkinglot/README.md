# Parking lot 
## Requirement
Parking lot is an area designated for parking cars. Parking lot has fixed
number multiple floor and each floor has fixed number of slots. Also it has multiple 
entry point and multiple exit points. At the entry point Multiple check points are installed that
disbursed ticket and at the exit customer needs to pay parking fee.

## Solution

### High level call flow diagram
```mermaid
sequenceDiagram
participant car as Vehicle
participant entry as Entry Gate
participant exit as Exit Gate
participant app as Parking lot service

rect rgb(250, 249, 246)
note right of car : Entering into parking lot
car ->> entry : GetParkingTicket()
entry ->> app : GetParkingTicket(Vehicle)
app -->> entry : ParkingTicket
entry -->> car : ParkingTicket
end
rect rgb(250, 249, 246)
note right of car : Exiting from parking lot
car ->> exit : Exit(ParkingTicket)
exit ->> app : Exit(ParkingTicket)
app -->> exit : ExitTicket
exit -->> car : ExitTicket
end
```
### UML Diagram
```mermaid
classDiagram
class `ExitTicket`        
class `ParkingTicket`
class `Slot`        
class `Vehicle`
class `Size`
`ExitTicket` --> `ParkingTicket`        
`Slot` --> `Vehicle`
`ParkingTicket` --> `Slot`        
`Vehicle` --> `Size`       
class Vehicle {
        +String vehicleNumber
        +Size size
}
class Size {
    <<enumeration>>
        SMALL, MEDIUM, LARGE, XL
}

class ParkingTicket {
        +Slot slot
        +Timestamp entryTime
}

class Slot {
    +String id
    +Vehicle vehicle    
}

class ExitTicket {
        +ParkingTicket parkingTicket
        +Timestamp exitTime
        +Money charges
}
```
### Deployment strategy
Assuming system is powering 10K parking lot and each parking lot getting 2 vehicles per minute.
QPS on GetParkingTicket API = (10K * 2)/60 ~= 300
QPS on Exit API ~= 300
Assuming average stay time is 2 hour so data needs to hold is 2*60*2*10K = 2400K ~ 1 GB
metadata storage is also 1 GB
Old data can be archived to cold data store to reduce cost.
This data can be store in the NoSQL database(DDB) as data can be partitioned based on the parking lot and tenant isolation can be achieved.
Instead of deploying it as a service it can be deployed as lambda.
