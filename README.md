# Bus Ticket Booking application 
This application is basically an online ticket booking application for bus that have been built using android studio framework, it has
lot of features, like it starts from the user opening the application and it has google authentication
implemented using firbase services , the user has to log into this application with their google account,
Login have been restricted such that only users having iit bhilai mail id can login into this application. After that
the user will be redirected to the home screen where we have different sections , the first section
includes the booking section where the user can fill the details like the source and destination place and
also the date for which the user wants to book the ticket, then after entering the details the user will be redirected to the next page where a list
of pages will be shown to him , and from the list the user can select the bus he wants to opt for then
after clicking on the bus name the user will be redirected to the page which includes a layout that provides
 the top view seat structure of the bus and from that structure the user can click on the vacant
seat, the booked and vacant seat are shown with different color, the booked seats are disabled from click
but on clicking the vacant seats the user will be shown a confirmation dialog and when confirmed
the user will be redirected to the payment page and for payment purposed I have integrated the paytm
gateway and after payment the user will be shown a confirmation and will be
redirected to the home screen again where we have other sections like the user can see the tickets which
are booked by him in the history section. Now the user can also cancel the ticket from their history section. Apart from it
there is also a section for admin section (not meant for common user) where the organizers of buses have to put a
password and the user id , and here the organizer can add new buses and reschedule the timing of existing buses and
can also delete the already scheduled buses. The details are correspondingly updated at the firebase realtime database. As
I proceeded through the project then a lot of difficulty arises that how we choose unique primary keys
for the database , we have to combine different fields to get unique values for the key, we also have to
normalize the database so that data redundancy is minimized as far as possible. Also in the application
we have to move information across the various activities using intents provided by the android sdk.
The technology used was android framework, mysqlite database, Firebase realtime database meant for storing the informations and data, Firebase authentication services,
Rest apis are also used, Paytm gateway api and the different optimization and techniques are implemented with the help of different data structures in Java.
