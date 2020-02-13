package na.zaliczenie.lukasz.demo;

import javax.persistence.*;

@Entity
@Table(name = "Obliczenia")
public class CalculateModel {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ObliczeniaId")
   public long Id;
    @Column(name = "X_double")
   public double x;
    @Column(name = "Y_double")
   public double y;
    @Column(name = "OPS_string")
   public String Funkcja;
}
