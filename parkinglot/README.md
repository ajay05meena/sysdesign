# Parking lot 
## Requirement
Parking lot is an area designated for parking cars. Parking lot has fixed
number multiple floor and each floor has fixed number of slots. Also it has multiple 
entry point and multiple exit points. At the entry point Multiple check points are installed that
disbursed ticket and at the exit customer needs to pay parking fee.

## Solution

High level call flow diagram
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