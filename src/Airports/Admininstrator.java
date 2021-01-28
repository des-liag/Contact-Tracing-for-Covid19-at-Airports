package Airports;

public class Admininstrator {
    
    enum TypeOfAddition {FLIGHT, FLIGHTCREW, TICKET, VISITEDSTORE  };
    public static boolean addition() {
        
        ProgramData programData = new ProgramData();
        TypeOfAddition add = null;
        Boolean flag = false;
        
        add = TypeOfAddition.FLIGHT;
        

        switch (add) {
            case FLIGHT:
                String depICAO = "LATI";
                String destICAO = "LAKU";
                String departureDateTime = "2020-12-13T13:00:00"; 
                String destinationDateTime = "2020-12-13T16:00:00";
                
                Airport departureAirport = null;
                Airport destinationAirport = null;
                
                
                for (Airport airport : programData.getAirports()) {
                    if (airport.getAirportICAO().equals(depICAO)) {
                        departureAirport = airport;
                    } else if(airport.getAirportICAO().equals(destICAO)) {
                        destinationAirport = airport;
                    }
                }

                if(departureAirport != null && destinationAirport != null) {
                    Flight flight = new Flight(departureAirport, destinationAirport, departureDateTime, destinationDateTime);
                    flag = true;
                }
            break;
            case FLIGHTCREW:
                String ssn = null; 
                String name = null;
                String lastName = null; 
                String address = null; 
                String phone = null;
                String flightId = null;
                
                int id = Integer.parseInt(flightId);
                boolean existsF = false;
                Person flightCrew = null;
                
                for(Flight flight: programData.getFlights()) {
                    if(flight.getFlightId() == id) {
                        existsF =  true;
                        break;
                    }
                }
                
                if (existsF) {
                    for(Person crew: programData.getFlightCrew()) {
                        if(crew.getSSN().equals(ssn)) {
                            flightCrew = crew;
                            break;
                        }
                    }
                    if(flightCrew == null) {
                        flightCrew = new Person(ssn, name, lastName, address, phone);
                    }
                    programData.getFlights().get(id).addFlightCrew(flightCrew);
                    flag = true;
                }
            break;
            case TICKET:
                String pasSSN = null;
                String pasName = null;
                String pasLastName = null; 
                String pasAddress = null; 
                String pasPhone = null;
                String flightIdString = null;
                String checkInDateTime = null;
                String luggage = null;
                String depGateName = null;
                String destGateName = null;
                String depIcao = null;
                String destIcao = null;
                
                int flightIdInt = Integer.parseInt(flightIdString);
                Boolean ifLuggage = Boolean.parseBoolean(luggage);
                boolean exists = false;
                AirportSection departureGate = null;
                AirportSection destinationGate = null;
                Person pass = null;
                boolean existsT = false;
                
                
                for(Flight flight: programData.getFlights()) {
                    if(flight.getFlightId() == flightIdInt) {
                        exists =  true;
                        break;
                    }
                }

                if (exists) {
                    for(Airport airport: programData.getAirports()) {
                        if (airport.getAirportICAO().equals(depIcao)) {
                            for (AirportSection section : airport.getGates()) {
                                if (section.getSectionName().equals(depGateName)) {
                                    departureGate = section;
                                }
                            }
                        } else if (airport.getAirportICAO().equals(destIcao)) {
                            for (AirportSection section : airport.getGates()) {
                                if (section.getSectionName().equals(destGateName)) {
                                    destinationGate = section;
                                }
                            }
                        }
                    }
                    if(departureGate != null && destinationGate != null) {
                        for (Person passenger : programData.getPassengers()) {
                            if(passenger.getSSN().equals(pasSSN)) {
                                pass = passenger;
                                break;
                            }
                        }
                        if(pass == null) {
                            pass = new Person(pasSSN, pasName, pasLastName, pasAddress, pasPhone);
                        }
                        for (Ticket ticket : programData.getFlights().get(flightIdInt).getTickets()) {
                            if (ticket.getPassenger().getSSN().equals(pass.getSSN())) {
                                existsT = true;
                            }
                        }
                        if(!existsT) {
                            programData.getFlights().get(flightIdInt).addTicket(
                                new Ticket(pass, checkInDateTime, ifLuggage, departureGate, destinationGate));
                            flag = true;
                        }
                    }
                }
            break;
            case VISITEDSTORE:
                String idString = null;
                String passengerSSN = null;
                String entranceDateTime = null;
                String storeName = null;
                
                boolean existsS = false;
                boolean existsStore = false;
                int idInt = Integer.parseInt(idString);
                AirportSection airportStore = null;
                
                for (Airport airport : programData.getAirports()) {
                    for(AirportSection section : airport.getStores()) {
                            if(section.getSectionName().equals(storeName)) {
                                airportStore = section;
                                existsStore = true;
                            }
                    }
                }
                if(existsStore) {
                    for(Flight flight: programData.getFlights()) {
                        if(flight.getFlightId() == idInt) {
                            for (Ticket ticket : flight.getTickets()) {
                                if(ticket.getPassenger().getSSN().equals(passengerSSN)) {
                                    for (VisitedStore store : ticket.getDepartureVisitedStores()) {
                                        if (store.getStore().getSectionName().equals(storeName)) {
                                            existsS = true;
                                        }
                                    }
                                    if(!existsS) {
                                        ticket.addDepartureVisitedStore(new VisitedStore(entranceDateTime, airportStore));
                                        flag = true;
                                    }
                                }
                            }   
                        }
                    }
                }
            break;             
            }
        return flag;
        }

}
