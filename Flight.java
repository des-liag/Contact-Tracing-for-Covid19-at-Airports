public class Flight {
    public ArrayList<Person> findCloseContactsOfPassenger(String ssn){
        ArrayList<Person> closeContacts = new ArrayList<Person>();
        try {
            LocalDateTime time = this.getTicketBySSN(ssn).getCheckInDateTime();

            closeContacts.add(this.getDepartureAirport().getWorkingEmployee(time));

            for(Person employee : this.getFlightCrew()){
                closeContacts.add(employee);
            }
            for(Person employee : this.getPasengers()){
                closeContacts.add(employee);
            }
        } catch(NullPointerException e) {
            System.out.println("No ticket found for this ssn");
            return null;
        }
        return closeContacts;
    }

    public ArrayList<Person> findCasualContactsOfPassenger(String ssn){
        ArrayList<Person> casualContacts = new ArrayList<Person>();
        LocalDateTime flightDateTime = this.getDepartureDateTime();

        for(AirportStuff employee : this.getTicketBySSN(ssn).getDepartureGate().getSectionStuff()){
            if(employee.isWorking(flightDateTime)){
                casualContacts.add(employee);
            }
        }

            for(VisitedStore visitedStore : this.getTicketBySSN(ssn).getDepartureVisitedStores()){
                for(AirportStuff employee : visitedStore.getStore().getSectionStuff()){
                    if(employee.isWorking(visitedStore.getEntranceDateTime())){
                        casualContacts.add(employee);
                    }
                }
            }
        return casualContacts;
    }

    public ArrayList<Person> getPasengers(){
        ArrayList<Person> passengers = new ArrayList<Person>();
        for(Ticket ticket : this.getTickets()){
            passengers.add(ticket.getPassenger());
        }
        return passengers;
    }
}
