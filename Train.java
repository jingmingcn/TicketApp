import java.io.Serializable;

public class Train implements Cloneable, Serializable {

    // default serialVersion id
    private static final long serialVersionUID = 1L;

    String depart;
    String destination;
    String id;
    int bizCabinNumber;
    int ecoCabinNumber;
    int bizCabinSeatNumber;
    int ecoCabinSeatNumber;

    public Train(String id, String depart, String destination, int bizCabinNumber, int ecoCabinNumber,
            int bizCabinSeatNumber, int ecoCabinSeatNumber) {
        this.id = id;
        this.depart = depart;
        this.destination = destination;
        this.bizCabinNumber = bizCabinNumber;
        this.ecoCabinNumber = ecoCabinNumber;
        this.bizCabinSeatNumber = bizCabinSeatNumber;
        this.ecoCabinSeatNumber = ecoCabinSeatNumber;
    }

    @Override
    protected Train clone() {
        Train clone = null;
        try {
            clone = (Train) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    public int getBizCabinNumber() {
        return this.bizCabinNumber;
    };

    public int getEcoCabinNumber() {
        return this.ecoCabinNumber;
    }

    public int getBizCabinSeatNumber() {
        return this.bizCabinSeatNumber;
    }

    public int getEcoCabinSeatNumber() {
        return this.ecoCabinSeatNumber;
    }

    public String getId() {
        return this.id;
    }

    public String getDepart() {
        return this.depart;
    }

    public String getDestination() {
        return this.destination;
    }

}