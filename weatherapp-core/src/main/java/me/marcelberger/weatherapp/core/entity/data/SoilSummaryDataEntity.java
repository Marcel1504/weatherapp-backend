package me.marcelberger.weatherapp.core.entity.data;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SoilSummaryDataEntity extends DataEntity {
    @Column(name = "amount")
    protected Integer amount;

    @Column(name = "temperature50cm_avg")
    protected Double temperature50cmAvg;

    @Column(name = "temperature50cm_min")
    protected Double temperature50cmMin;

    @Column(name = "temperature50cm_max")
    protected Double temperature50cmMax;

    @Column(name = "temperature100cm_avg")
    protected Double temperature100cmAvg;

    @Column(name = "temperature100cm_min")
    protected Double temperature100cmMin;

    @Column(name = "temperature100cm_max")
    protected Double temperature100cmMax;

    @Column(name = "temperature200cm_avg")
    protected Double temperature200cmAvg;

    @Column(name = "temperature200cm_min")
    protected Double temperature200cmMin;

    @Column(name = "temperature200cm_max")
    protected Double temperature200cmMax;
}
