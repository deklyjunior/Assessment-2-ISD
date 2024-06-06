import java.util.*;

public class Main {
    static class City {
        String name;
        double x, y; 

        City(String name, double x, double y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }

        double jarakKe(City other) {
            return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
        }
    }

    public static void main(String[] args) {
        List<City> cities = Arrays.asList(
            new City("Paris", 0, 0),
            new City("Brussels", 1, 1),
            new City("Zurich", 2, 1),
            new City("Amsterdam", 2, 2),
            new City("Vienna", 3, 0),
            new City("Prague", 4, 2),
            new City("Hamburg", 3, 3),
            new City("Warsaw", 5, 3),
            new City("Budapest", 6, 1)
        );

        City startCity = cities.get(0);

        List<City> ruteTerdekat = cariRuteTerdekat(cities, startCity);
        System.out.println("Route Terdekat Yang Bisa Dikunjungi Dari Perancis:");
        for (City city : ruteTerdekat) {
            System.out.print(city.name + " -> ");
        }
        System.out.println();

        List<City> sortedByDistance = sortByDistance(cities, startCity);
        System.out.println("Route Terjauh Yang Bisa Dikunjungi Dari Perancis:");
        for (City city : sortedByDistance) {
            System.out.print(city.name + " -> ");
        }
    }

    private static List<City> cariRuteTerdekat(List<City> cities, City startCity) {
        List<City> route = new ArrayList<>();
        Set<City> visited = new HashSet<>();
        City currentCity = startCity;

        while (route.size() < cities.size()) {
            route.add(currentCity);
            visited.add(currentCity);

            City nearestCity = null;
            double jarakTerdekat = Double.MAX_VALUE;

            for (City city : cities) {
                if (!visited.contains(city)) {
                    double distance = currentCity.jarakKe(city);
                    if (distance < jarakTerdekat) {
                        jarakTerdekat = distance;
                        nearestCity = city;
                    }
                }
            }
            if (nearestCity != null) {
                currentCity = nearestCity;
            }
        }
        return route;
    }

    private static List<City> sortByDistance(List<City> cities, City startCity) {
        List<City> sortedCities = new ArrayList<>(cities);
        sortedCities.sort(Comparator.comparingDouble(city -> city.jarakKe(startCity)));
        return sortedCities;
    }
}
