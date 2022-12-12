package academy.digitallab.test.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "tbl_blood")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Blood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private LocalDate dateCreated;
    @Max(100)
    @Min(0)
    private float sugarLevel;
    @Max(100)
    @Min(0)
    private float fatLevel;
    @Max(100)
    @Min(0)
    private float oxygenLevel;

    private String risk;



    public Blood(String id, LocalDate dateCreated, float sugarLevel, float fatLevel, float oxygenLevel, String risk) {
        this.id = UUID.randomUUID().toString();
        this.dateCreated = LocalDate.now();
        this.sugarLevel = sugarLevel;
        this.fatLevel = fatLevel;
        this.oxygenLevel = oxygenLevel;
        this.risk = calcHealthRisk();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public float getSugarLevel() {
        return sugarLevel;
    }

    public void setSugarLevel(float sugarLevel) {
        this.sugarLevel = sugarLevel;
    }

    public float getFatLevel() {
        return fatLevel;
    }

    public void setFatLevel(float fatLLevel) {
        this.fatLevel = fatLevel;
    }

    public float getOxyBlood() {
        return oxygenLevel;
    }

    public void setOxygenLevel(float oxygenLevel) {
        this.oxygenLevel = oxygenLevel;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String calcHealthRisk() {
        String status= "";
        if (sugarLevel > 70 && fatLevel > 88.5 && oxygenLevel < 60) {
            status = "High risk of disease";
        }
        if ((sugarLevel > 50 && sugarLevel < 70) && (fatLevel > 62.2 && fatLevel < 88.8) && (oxygenLevel > 60 && oxygenLevel < 70)) {
            status = "Moderate risk of disease";
        }
        if ((sugarLevel < 50 && fatLevel < 62.2 && oxygenLevel > 70)) {
            status = "low risk of disease";
        }
        return status;
    }
}