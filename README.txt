Background
Build a website to service booking hotel online.
Program Specifications
In this assignment, you are requested to develop a booking website. You must use Servlet as Controller and follow 
MVC2 model.
Features:
This system contains the following functions:
- Function 1: Login 
o In order to booking, an authentication is required.
o If the user has not authenticated, the system redirects to the registration page.
o The actor enters userID and password, the function checks if the userID with the password is in the 
available user list, then grant the access permission. If not, a message would appear no notify that user is 
not found.
o Login function is required for shopping.
- Function 2: Display- Search 
o List all hotel which has an available room in the system.
o Each hotel has some kind of room: single, double, family, …
o Each room type has different price.
o User can find the room based on hotel name or hotel area and check in date and check out date and 
amount of room. 
o All users can use this function (login is not required)
- Function 3: Registration 
o Register new user: email as ID, phone, name, address, create date.
o Create date is current date.
o The default status of new user is active.
o Password must be encrypted using SHA-256 before store in database. 
- Function 4: Booking 
o All users can use this function except admin role (login is required)
o Add the selected room to booking cart.
o Each user can book any available room in the list (not limit the amount room want to book)
o User can view the selected room in the cart. For each room: hotel name, room type, amount, price, 
total. The screen must show the total amount of money of this cart.
o User can remove the room from the cart. The confirm message will show before delete action.
o User can update amount of each room in cart.
o Click the Confirm button to store the booking to database (must store the buy date time). The warning 
message will show if the selected room is out of stock.
o During booking user enter the discount code (if any). Each discount code has its expiry date.
- Function 5: Booking history 
o User can take over the booking history: list of booking order by booking date.
o Support search function: search by name or booking date
o Support delete function to delete the booking (update the status of booking to inactive).
- Function 6: Booking confirm by email – 100 LOC (extra)
- Function 7: Feedback on the quality of room service – 50 LOC (Extra)
o The user sends a feedback to the quality of the room he has booked.
o Rating is performed only when the user has used the service. 
o Rating on a scale of: from 0 to 10
