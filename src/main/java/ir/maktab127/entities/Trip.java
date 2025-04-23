package ir.maktab127.entities;

public class Trip {

    private Long id;
    private Driver driver;
    private Passenger passenger;
    private Location begin;
    private  Location destination;
    private double price;
    private TripStatus tripStatus;
    public Trip(Passenger passenger,Location begin,Location destination){

        this.passenger=passenger;
        this.driver=null;
        this.begin=begin;
        this.destination=destination;
        this.price=caculateTripPrice();
        tripStatus=TripStatus.WAITING;
    }

    private double caculateTripPrice() {
        int xdef=Math.abs(begin.getX()-destination.getX());
        int ydef=Math.abs(begin.getY()-destination.getY());
        return (xdef+ydef)*1000;
    }
    public void tripTime()   {
        int xdef=Math.abs(begin.getX()-destination.getX());
        int ydef=Math.abs(begin.getY()-destination.getY());

        try {
            Thread.sleep(  (xdef+ydef)* 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Location getBegin() {
        return begin;
    }

    public void setBegin(Location begin) {
        this.begin = begin;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;

    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
