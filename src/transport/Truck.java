package transport;

import java.util.List;
import java.util.Objects;

public class Truck extends Transport<DriverC> {
    private LoadCapacity truck;

    public Truck(String brand, String model, double engineVolume, DriverC driver, LoadCapacity truck, List<Mechanic> mechanics) {
        super(brand, model, engineVolume, driver, mechanics);
        this.truck = truck;
    }

    public LoadCapacity getTruck() {
        return truck;
    }

    @Override
    public void runDiagnostic() {
        System.out.println("Диагностика пройдена " + getBrand() + " " + getModel());
    }

    public enum LoadCapacity {
        N1(0, 3.5),
        N2(3.5, 12),
        N3(12, 20);
        private double min;
        private double max;

        LoadCapacity(double min, double max) {
            this.min = min;
            this.max = max;

        }

        public double getMin() {
            return min;
        }

        public double getMax() {
            return max;
        }

        public static LoadCapacity getValue(double value) {
            for (LoadCapacity e : LoadCapacity.values()) {
                if (value >= e.getMin() && value <= e.getMax()) {
                    System.out.println(e);
                    return e;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return super.toString() +
                    " от " + min +
                    " до " + max + " тонн";
        }
    }

    @Override
    public String toString() {
        return super.toString() + ". Грузоподъемность: " + truck;
    }

    @Override
    public void startMov() {
        if (gasTankBar <= 0) {
            throw new EmptyGasTankException(this);
        }
        if (oilTankBar <= 0) {
            throw new NoOilException(this);
        }
        System.out.println("Грузовик " + getBrand() + "начал движение");
    }

    @Override
    public void finishMov(double oilLiters, double gasLiters) {
        gasTankBar -= gasLiters;
        oilTankBar -= oilLiters;
        System.out.println("Грузовик " + getBrand() + "закончил движение");

    }

    @Override
    public Type getType() {
        return Type.TRUCK;
    }

    @Override
    public void pitStop() {
        System.out.println("Пит-стоп у грузовика " + getBrand());

    }


    @Override
    public void bestLapTime() {
        int minBound = 80;
        int maxBound = 120;
        int bestLapTime = (int) (minBound + (maxBound - minBound) * Math.random());
        System.out.println("Лучшее время круга для грузовика" + bestLapTime);

    }

    @Override
    public void maxSpeed() {
        int minBound = 80;
        int maxBound = 120;
        int maxSpeed = (int) (minBound + (maxBound - minBound) * Math.random());
        System.out.println("Максимальная скорость для грузовика" + maxSpeed);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Truck truck1 = (Truck) o;
        return truck == truck1.truck;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), truck);
    }
}
