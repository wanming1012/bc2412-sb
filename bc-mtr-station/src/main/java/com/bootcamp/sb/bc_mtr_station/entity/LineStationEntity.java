package com.bootcamp.sb.bc_mtr_station.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "LineStations")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LineStationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "line_id")
  private LineEntity line;

  @ManyToOne
  @JoinColumn(name = "current_station_id")
  private StationEntity currentStation;

  @ManyToOne
  @JoinColumn(name = "previous_station_id")
  private StationEntity previousStation;

  @ManyToOne
  @JoinColumn(name = "next_station_id")
  private StationEntity nextStation;
}
