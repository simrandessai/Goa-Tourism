/**
 * @author Simran
 * Roll No: 2650
 * Description: This is a Goa tourism booking system implemented in Java.
 * It sets up a small set of demo packages and places to stay and provides a simple menu-driven interface for tourists.
 * Anyone can browse packages, places to stay and nearby destinations without giving any details.
 * Tourist details are only asked for when a package is booked.
 * The program demonstrates single, multilevel, hierarchical, multiple and hybrid inheritance.
 */

import java.util.ArrayList;
import java.util.Scanner;
// 
/** Base class for all Goa tourism packages. */
abstract class Tourism {
    protected String packageName;
    protected String places;
    protected double cost;
    protected int days;
    protected String nearby;

    Tourism(String packageName, String places, double cost, int days, String nearby) {
        this.packageName = packageName;
        this.places = places;
        this.cost = cost;
        this.days = days;
        this.nearby = nearby;
    }

    /** Returns the category of the package. */
    abstract String category();

    /** Displays the common package details. */
    void show() {
        System.out.println("   Package  : " + packageName);
        System.out.println("   Places   : " + places);
        System.out.println("   Duration : " + days + (days == 1 ? " day" : " days"));
        System.out.println("   Cost     : Rs. " + (int) cost + " per person");
        System.out.println("   Nearby   : " + nearby);
    }
}

// SINGLE INHERITANCE: Tourism -> GoaTourism
/** General Goa sightseeing package. */
class GoaTourism extends Tourism {
    GoaTourism(String packageName, String places, double cost, int days, String nearby) {
        super(packageName, places, cost, days, nearby);
    }
    /** Returns the category of the package. */
    String category() { return "Goa Sightseeing"; }
    /** Displays the package details. */
    void details() {
        show();
    }
}

// MULTILEVEL INHERITANCE: Tourism -> GoaTourism -> BeachTourism
/** Beach package. */
class BeachTourism extends GoaTourism {
    protected String highlight;
    BeachTourism(String packageName, String places, double cost, int days, String nearby,
                 String highlight) {
        super(packageName, places, cost, days, nearby);
        this.highlight = highlight;
    }
    /** Returns the category of the package. */
    String category() { return "Beach Tourism"; }
    /** Displays the package details. */
    void details() {
        super.details();
        System.out.println("   Highlight: " + highlight);
    }
}

// HIERARCHICAL INHERITANCE: HeritageTourism, AdventureTourism, WildlifeTourism and ComboTourism all extend GoaTourism.
/** Heritage package. */
class HeritageTourism extends GoaTourism {
    HeritageTourism(String packageName, String places, double cost, int days, String nearby) {
        super(packageName, places, cost, days, nearby);
    }
    /** Returns the category of the package. */
    String category() { return "Heritage Tourism"; }
    /** Displays the package details. */
    void details() {
        super.details();
        System.out.println("   Heritage : Portuguese-era churches (UNESCO World Heritage Site)");
    }
}

/** Adventure package. */
class AdventureTourism extends GoaTourism {
    AdventureTourism(String packageName, String places, double cost, int days, String nearby) {
        super(packageName, places, cost, days, nearby);
    }
    /** Returns the category of the package. */
    String category() { return "Adventure Tourism"; }
    /** Displays the package details. */
    void details() {
        super.details();
        System.out.println("   Activity : Parasailing, jet ski and banana boat ride");
    }
}
/** Wildlife and nature package. */
class WildlifeTourism extends GoaTourism {
    WildlifeTourism(String packageName, String places, double cost, int days, String nearby) {
        super(packageName, places, cost, days, nearby);
    }
    /** Returns the category of the package. */
    String category() { return "Wildlife & Nature Tourism"; }
    /** Displays the package details. */
    void details() {
        super.details();
        System.out.println("   Best time: October to May");
    }
}
/** Multi-day tour package with a day-wise itinerary. */
class ComboTourism extends GoaTourism {
    private String[] itinerary;
    ComboTourism(String packageName, String places, double cost, int days, String nearby,
                 String[] itinerary) {
        super(packageName, places, cost, days, nearby);
        this.itinerary = itinerary;
    }

    /** Returns the category of the package. */
    String category() { return "Tour Package"; }

    /** Displays the package details and itinerary. */
    void details() {
        super.details();
        for (int i = 0; i < itinerary.length; i++) {
            System.out.println("   Day " + (i + 1) + "    : " + itinerary[i]);
        }
    }
}

/** Water based activities. */
interface WaterActivity {
    /** Displays the water activity. */
    void waterSports();
}

/** Guided tours. */
interface TouristGuide {
    /** Displays the guide. */
    void guide();
}

// MULTIPLE INHERITANCE: ScubaDiving extends BeachTourism and implements WaterActivity.
/** Scuba diving package. */
class ScubaDiving extends BeachTourism implements WaterActivity {

    ScubaDiving(String packageName, String places, double cost, int days, String nearby) {
        super(packageName, places, cost, days, nearby, "Coral reefs and clear water");
    }

    /** Returns the category of the package. */
    String category() { return "Scuba Diving"; }

    /** Displays the water activity. */
    public void waterSports() {
        System.out.println("   Water Activity: Scuba Diving");
    }

    /** Displays the package details. */
    void details() {
        super.details();
        waterSports();
    }
}

// HYBRID INHERITANCE: GuidedScubaTour extends ScubaDiving (multilevel) and implements TouristGuide (multiple).
/** Scuba package with a tourist guide. */
class GuidedScubaTour extends ScubaDiving implements TouristGuide {

    GuidedScubaTour(String packageName, String places, double cost, int days, String nearby) {
        super(packageName, places, cost, days, nearby);
    }

    /** Returns the category of the package. */
    String category() { return "Guided Scuba Tour"; }

    /** Displays the guide. */
    public void guide() {
        System.out.println("   Guide    : Professional Tourist Guide");
    }

    /** Displays the package details. */
    void details() {
        super.details();
        guide();
    }
}

/** A place to stay. */
class Stay {
    final String name;
    final String area;
    final double pricePerNight;

    Stay(String name, String area, double pricePerNight) {
        this.name = name;
        this.area = area;
        this.pricePerNight = pricePerNight;
    }
}

/** A booking made by a tourist, with its bill. */
class Booking {
    private static int counter = 1000;

    final String id;
    final String touristName;
    final String phone;
    final Tourism pkg;
    final Stay stay;
    final int persons;
    final int rooms;
    final int nights;

    final double tourFare;
    final double stayFare;
    final double gst;
    final double total;
    String status = "CONFIRMED";

    Booking(String touristName, String phone, Tourism pkg, int persons, Stay stay) {
        counter++;
        this.id = "GOA" + counter;
        this.touristName = touristName;
        this.phone = phone;
        this.pkg = pkg;
        this.persons = persons;
        this.stay = stay;

        this.nights = pkg.days - 1;
        this.rooms = (persons + 1) / 2;
        this.tourFare = pkg.cost * persons;
        this.stayFare = (stay == null) ? 0 : stay.pricePerNight * rooms * nights;
        this.gst = (tourFare + stayFare) * 0.05;
        this.total = tourFare + stayFare + gst;
    }

    /** Prints the booking receipt. */
    void printReceipt() {
        System.out.println("\n     BOOKING RECEIPT     ");
        System.out.println("Booking ID : " + id + " (" + status + ")");
        System.out.println("Tourist    : " + touristName + " (" + phone + ")");
        System.out.println("Package    : " + pkg.packageName + ", " + persons + " person(s)");
        System.out.println("Stay       : " + (stay == null ? "Not included"
                : stay.name + " - " + rooms + " room(s) x " + nights + " night(s)"));
        System.out.printf("Tour fare  : Rs. %.2f%n", tourFare);
        System.out.printf("Stay       : Rs. %.2f%n", stayFare);
        System.out.printf("GST (5%%)   : Rs. %.2f%n", gst);
        System.out.printf("TOTAL      : Rs. %.2f%n", total);
        System.out.println("______________________________________");
    }
}

/** Main class of the Goa tourism booking system. */
public class GoaTourismBooking {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Tourism> packages = new ArrayList<Tourism>();
    static ArrayList<Stay> stays = new ArrayList<Stay>();
    static ArrayList<Booking> bookings = new ArrayList<Booking>();


    /** Reads a whole number within the given range. */
    static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException e) {
            }
            System.out.println("Please enter a number between " + min + " and " + max + ".");
        }
    }

    /** Reads a non-empty line of text. */
    static String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = sc.nextLine().trim();
            if (!text.isEmpty()) {
                return text;
            }
            System.out.println("This field cannot be empty.");
        }
    }


    /** Loads the demo packages and places to stay. */
    static void loadData() {
        packages.add(new GoaTourism("Goa City Tour", "Panaji, Miramar, Dona Paula",
                3500, 1, "Old Goa, Fontainhas"));
        packages.add(new BeachTourism("North Goa Beach Trail", "Calangute, Baga, Anjuna",
                6500, 3, "Fort Aguada, Mapusa", "Beach shacks and nightlife"));
        packages.add(new HeritageTourism("Old Goa Heritage Walk", "Basilica of Bom Jesus, Se Cathedral",
                1800, 1, "Fontainhas, Divar Island"));
        packages.add(new AdventureTourism("Water Sports Splash", "Baga, Calangute",
                2500, 1, "Fort Aguada, Sinquerim"));
        packages.add(new WildlifeTourism("Dudhsagar Falls Safari", "Dudhsagar Falls, Mollem",
                3500, 2, "Tambdi Surla Temple, Ponda"));
        packages.add(new ScubaDiving("Grande Island Scuba", "Grande Island, Vasco",
                4500, 1, "Bogmalo Beach"));
        packages.add(new GuidedScubaTour("Guided Scuba Tour", "Grande Island, Vasco",
                6500, 1, "Bogmalo Beach"));
        packages.add(new ComboTourism("Goa Classic Tour (3 Days)", "North Goa, Old Goa, South Goa",
                8500, 3, "Cabo de Rama, Ponda",
                new String[] { "Panaji, Miramar and Dona Paula",
                               "Old Goa, Fort Aguada and Baga beach",
                               "Colva beach and Margao market" }));

        stays.add(new Stay("Calangute Beachside Guesthouse", "Calangute", 1800));
        stays.add(new Stay("Panaji Riverside Hotel", "Panaji", 3500));
        stays.add(new Stay("Colva Sea-View Resort", "Colva", 4200));
        stays.add(new Stay("Benaulim Luxury Beach Resort", "Benaulim", 9500));
    }


    /** Displays all packages. */
    static void listPackages() {
        System.out.println("\n     GOA TOURISM PACKAGES     ");
        for (int i = 0; i < packages.size(); i++) {
            System.out.println("\n[" + (i + 1) + "] " + packages.get(i).category().toUpperCase());
            ((GoaTourism) packages.get(i)).details();
        }
    }


    /** Displays the places to stay. */
    static void listStays() {
        System.out.println("\n     PLACES TO STAY     ");
        for (int i = 0; i < stays.size(); i++) {
            Stay s = stays.get(i);
            System.out.println("[" + (i + 1) + "] " + s.name + ", " + s.area
                    + " - Rs. " + (int) s.pricePerNight + " per room per night");
        }
    }

    /** Displays the nearby destinations. */
    static void nearbyDestinations() {
        System.out.println("\n     NEARBY DESTINATIONS (from Panaji)     ");
        System.out.println(" - Old Goa (~10 km)         : UNESCO churches");
        System.out.println(" - Calangute & Baga (~15 km): busy beaches and water sports");
        System.out.println(" - Ponda (~28 km)           : temples and spice farms");
        System.out.println(" - Colva (~38 km)           : long white-sand beach");
        System.out.println(" - Dudhsagar Falls (~70 km) : tiered waterfall");
        System.out.println(" - Gokarna, Karnataka (~140 km): day trip beyond Goa");
    }


    /** Books a package for a tourist. */
    static void bookPackage() {
        listPackages();
        int choice = readInt("\nEnter package number to book (0 to cancel): ", 0, packages.size());
        if (choice == 0) {
            return;
        }
        Tourism pkg = packages.get(choice - 1);

        String name = readText("Tourist name: ");
        String phone = readText("Mobile number: ");
        int persons = readInt("Number of persons (1-20): ", 1, 20);


        Stay chosen = null;
        if (pkg.days > 1) {
            listStays();
            int s = readInt("Enter stay number (0 = I will arrange my own): ", 0, stays.size());
            if (s > 0) {
                chosen = stays.get(s - 1);
            }
        }

        Booking booking = new Booking(name, phone, pkg, persons, chosen);
        booking.printReceipt();

        if (readText("Confirm booking? (y/n): ").equalsIgnoreCase("y")) {
            bookings.add(booking);
            System.out.println("Booking confirmed! Your Booking ID is " + booking.id + ".");
        } else {
            System.out.println("Booking discarded.");
        }
    }

    /** Displays the bookings of a mobile number. */
    static void viewBookings() {
        String phone = readText("Mobile number: ");
        boolean found = false;
        for (Booking b : bookings) {
            if (b.phone.equals(phone)) {
                System.out.printf("%s | %s | %d person(s) | Rs. %.2f | %s%n",
                        b.id, b.pkg.packageName, b.persons, b.total, b.status);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No bookings found for this number.");
        }
    }

    /** Cancels a booking with an 80% refund. */
    static void cancelBooking() {
        String id = readText("Booking ID (e.g. GOA1001): ").toUpperCase();
        String phone = readText("Mobile number: ");
        for (Booking b : bookings) {
            if (b.id.equals(id) && b.phone.equals(phone)) {
                if (b.status.equals("CANCELLED")) {
                    System.out.println("This booking is already cancelled.");
                } else {
                    b.status = "CANCELLED";
                    System.out.printf("Booking %s cancelled. Refund (80%%): Rs. %.2f%n", b.id, b.total * 0.80);
                }
                return;
            }
        }
        System.out.println("No matching booking found.");
    }


    /** Shows the inheritance demo menu. */
    static void inheritanceDemo() {
        int choice;
        do {
            System.out.println("\n     INHERITANCE DEMO     ");
            System.out.println("1. Single Inheritance");
            System.out.println("2. Multilevel Inheritance");
            System.out.println("3. Hierarchical Inheritance");
            System.out.println("4. Multiple Inheritance");
            System.out.println("5. Hybrid Inheritance");
            System.out.println("6. Back");
            choice = readInt("Enter choice: ", 1, 6);

            switch (choice) {
                case 1:
                    packages.get(0).show();
                    break;
                case 2:
                    ((BeachTourism) packages.get(1)).details();
                    break;
                case 3:
                    ((HeritageTourism) packages.get(2)).details();
                    System.out.println();
                    ((AdventureTourism) packages.get(3)).details();
                    break;
                case 4:
                    ((ScubaDiving) packages.get(5)).details();
                    break;
                case 5:
                    ((GuidedScubaTour) packages.get(6)).details();
                    break;
                default:
                    break;
            }
        } while (choice != 6);
    }

    /** Shows the main menu. */
    public static void main(String[] args) {
        loadData();
        int choice;

        do {
            System.out.println("\n____________________________________");           
            System.out.println("\n     GOA TOURISM BOOKING SYSTEM     ");
            System.out.println("____________________________________");           
            System.out.println("\n1. View Packages");
            System.out.println("2. Places to Stay");
            System.out.println("3. Nearby Destinations");
            System.out.println("4. Book a Package");
            System.out.println("5. View My Bookings");
            System.out.println("6. Cancel a Booking");
            System.out.println("7. Inheritance Demo");
            System.out.println("8. Exit");
            choice = readInt("Enter choice: ", 1, 8);

            switch (choice) {
                case 1: listPackages(); break;
                case 2: listStays(); break;
                case 3: nearbyDestinations(); break;
                case 4: bookPackage(); break;
                case 5: viewBookings(); break;
                case 6: cancelBooking(); break;
                case 7: inheritanceDemo(); break;
                case 8: System.out.println("Thank You!"); break;
                default: break;
            }
        } while (choice != 8);

        sc.close();
    }
}