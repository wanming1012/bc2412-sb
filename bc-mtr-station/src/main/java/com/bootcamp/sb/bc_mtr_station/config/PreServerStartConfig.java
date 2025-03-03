package com.bootcamp.sb.bc_mtr_station.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.bootcamp.sb.bc_mtr_station.entity.LineEntity;
import com.bootcamp.sb.bc_mtr_station.entity.LineStationEntity;
import com.bootcamp.sb.bc_mtr_station.entity.StationEntity;
import com.bootcamp.sb.bc_mtr_station.repository.LineRepository;
import com.bootcamp.sb.bc_mtr_station.repository.LineStationRepository;
import com.bootcamp.sb.bc_mtr_station.repository.StationRepository;

@Component
public class PreServerStartConfig implements CommandLineRunner {
  @Autowired
  private LineRepository lineRepository;

  @Autowired
  private StationRepository stationRepository;

  @Autowired
  private LineStationRepository lineStationRepository;

  @Override
  public void run(String... args) throws Exception {
    Map<String, LineEntity> lineEntities = addLines(
        List.of("AEL", "TCL", "TML", "TKL", "EAL", "SIL", "TWL", "ISL", "KTL"));

    addStations(lineEntities.get("AEL"),
        List.of("HOK", "KOW", "TSY", "AIR", "AWE"));
    addStations(lineEntities.get("TCL"),
        List.of("HOK", "KOW", "OLY", "NAC", "LAK", "TSY", "SUN", "TUC"));
    addStations(lineEntities.get("TML"),
        List.of("WKS", "MOS", "HEO", "TSH", "SHM", "CIO", "STW", "CKT", "TAW",
            "HIK", "DIH", "KAT", "SUW", "TKW", "HOM", "HUH", "ETS", "AUS",
            "NAC", "MEF", "TWW", "KSR", "YUL", "LOP", "TIS", "SIH", "TUM"));
    addStations(lineEntities.get("TKL"),
        List.of("NOP", "QUB", "YAT", "TIK", "TKO", "LHP", "HAH", "POA"));
    addStations(lineEntities.get("EAL"),
        List.of("ADM", "EXC", "HUH", "MKK", "KOT", "TAW", "SHT", "FOT", "RAC",
            "UNI", "TAP", "TWO", "FAN", "SHS", "LOW", "LMC"));
    addStations(lineEntities.get("SIL"),
        List.of("ADM", "OCP", "WCH", "LET", "SOH"));
    addStations(lineEntities.get("TWL"),
        List.of("CEN", "ADM", "TST", "JOR", "YMT", "MOK", "PRE", "SSP", "CSW",
            "LCK", "MEF", "LAK", "KWF", "KWH", "TWH", "TSW"));
    addStations(lineEntities.get("ISL"),
        List.of("KET", "HKU", "SYP", "SHW", "CEN", "ADM", "WAC", "CAB", "TIH",
            "FOH", "NOP", "QUB", "TAK", "SWH", "SKW", "HFC", "CHW"));
    addStations(lineEntities.get("KTL"),
        List.of("WHA", "HOM", "YMT", "MOK", "PRE", "SKM", "KOT", "LOF", "WTS",
            "DIH", "CHH", "KOB", "NTK", "KWT", "LAT", "YAT", "TIK"));


  }

  private Map<String, LineEntity> addLines(List<String> names) {
    Map<String, LineEntity> lineEntities = new HashMap<>();
    for (String name : names) {
      LineEntity lineEntity =
          this.lineRepository.save(LineEntity.builder().name(name).build());
      lineEntities.put(name, lineEntity);
    }
    return lineEntities;
  }

  private void addStations(LineEntity lineEntity, List<String> names) {
    List<StationEntity> stationEntities = new ArrayList<>();
    for (String name : names) {
      Optional<StationEntity> stationEntityOptional = this.stationRepository.findByName(name);
      StationEntity stationEntity = null;
      if (stationEntityOptional.isPresent()) {
        stationEntity = stationEntityOptional.get();
      } else {
        stationEntity = this.stationRepository.save(StationEntity.builder().name(name).build());
      }
          // this.stationRepository.findByName(name).orElse(this.stationRepository
          //     .save(StationEntity.builder().name(name).build()));
      stationEntities.add(stationEntity);
    }

    for (int i = 0; i < stationEntities.size(); i++) {
      StationEntity previousStationEntity =
          i == 0 ? null : stationEntities.get(i - 1);
      StationEntity nextStationEntity =
          i == stationEntities.size() - 1 ? null : stationEntities.get(i + 1);
      this.lineStationRepository.save(LineStationEntity.builder()
          .line(lineEntity).currentStation(stationEntities.get(i))
          .previousStation(previousStationEntity).nextStation(nextStationEntity)
          .build());
    }
  }
}
