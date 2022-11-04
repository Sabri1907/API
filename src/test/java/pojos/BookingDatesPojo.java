package pojos;

public class BookingDatesPojo {

    //1. ADIM=> Tum key'ler icin private variable'lar olusturulur
    private String checkin;
    private String checkout;

    // 2.ADIM=> Tum parametrelerle ve parametresiz constructorlar olusturulur


    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public BookingDatesPojo() {

    }

    // 3.ADIM=> Public Getter ve Setetr method'larini olusturuyoruz

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    // 4.ADIM=> toString() methodu olusturulur

    @Override
    public String toString() {
        return "BookingDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
