package ro.phd.vsp.cpoprocessor.service;

import static ro.phd.vsp.cpoprocessor.utils.DomainMapper.nttTOCrtMetadata;
import static ro.phd.vsp.cpoprocessor.utils.DomainMapper.nttTOMetadata;
import static ro.phd.vsp.cpoprocessor.utils.DomainMapper.nttToCrtData;
import static ro.phd.vsp.cpoprocessor.utils.DomainMapper.nttToData;

import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.phd.vsp.cpoprocessor.model.SensorData;
import ro.phd.vsp.cpoprocessor.model.logic.SensorDataNtt;
import ro.phd.vsp.cpoprocessor.repository.CrtSensorDataRepository;
import ro.phd.vsp.cpoprocessor.repository.CrtSensorMetadataRepository;
import ro.phd.vsp.cpoprocessor.repository.SensorDataRepository;
import ro.phd.vsp.cpoprocessor.repository.SensorMetadataRepository;

@Service
@RequiredArgsConstructor
public class DataService {

  private final CrtSensorDataRepository crtDataRepository;
  private final CrtSensorMetadataRepository crtMetadataRepository;
  private final SensorDataRepository dataRepository;
  private final SensorMetadataRepository metadataRepository;

  public void saveValues(SensorDataNtt data) {
    saveCrtValues(data);
    saveHistoryValues(data);
  }

  private void saveCrtValues(SensorDataNtt data) {
    crtDataRepository.save(nttToCrtData(data));
    crtMetadataRepository.save(nttTOCrtMetadata(data));
  }

  private void saveHistoryValues(SensorDataNtt data) {
    dataRepository.save(nttToData(data));
    metadataRepository.save(nttTOMetadata(data));
  }

  public List<SensorData> getAllByUpdatedOn(UUID sid, long amountToSubstract,
      TemporalUnit unitToSubstract) {
    return dataRepository.findBySidAndUpdatedOnGreaterThanEqual(sid,
        LocalDateTime.now().minus(amountToSubstract, unitToSubstract));
  }

}
